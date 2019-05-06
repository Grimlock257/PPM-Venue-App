package com.java.ppm.vii.thepoint.user;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.java.ppm.vii.thepoint.R;
import com.java.ppm.vii.thepoint.database.API;
import com.java.ppm.vii.thepoint.database.HTTPMethod;
import com.java.ppm.vii.thepoint.database.RequestHandler;
import com.java.ppm.vii.thepoint.database.entity.About;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static android.view.View.GONE;

public class AboutFragment extends Fragment {

    private ArrayList<About> aboutList;

    AboutAdapter adapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initLayoutViews(view);

        fetchAboutUs();
    }

    /**
     * Initialise the layout view elements and set listeners, if applicable
     */
    private void initLayoutViews(View view) {
        // Create references to views on the layout
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    /**
     * Create a NetworkRequest to retrieve the About Us sections from the database
     */
    private void fetchAboutUs() {
        NetworkRequest request = new NetworkRequest(API.ABOUT.getURL(), null, HTTPMethod.GET);
        request.execute();
    }

    /**
     * This method refreshes the RecyclerView for the About Us list by taking in the new data and
     * updating the RecyclerView
     *
     * @param aboutus The JSONArray of About Us sections (as this data is returned from the database)
     *
     * @throws JSONException If the JSONArray is malformed or missing keys
     */
    private void updateAbout(JSONArray aboutus) throws JSONException {
        aboutList = new ArrayList<>();

        // Fetch all the About Us sections from the JSONArray and convert them to About objects
        // and store them into the ArrayList
        for (int i = 0; i < aboutus.length(); i++) {
            JSONObject obj = aboutus.getJSONObject(i);

            aboutList.add(new About(
                    obj.getInt("id"),
                    obj.getString("title"),
                    obj.getString("info"),
                    obj.getString("img")
            ));
        }

        // Update the RecyclerView to reflect the new aboutList
        updateRecyclerView();
    }

    /**
     * This method updates the RecyclerView using the using the aboutList.
     */
    public void updateRecyclerView() {
        adapter = new AboutAdapter(aboutList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new AboutAdapter.OnItemClickListener() {
            @Override
            public void onDirectionsButtonClicked() {
                // Make sure the position returned is a valid position
                Uri getDirections = Uri.parse("https://www.google.co.uk/maps/dir//52.9122315,-1.1837789/@52.9122315,-1.1837789,19z");
                Intent directionsIntent = new Intent(Intent.ACTION_VIEW, getDirections);

                // Check if there is an activity to handle this intent, if so, open it
                if (directionsIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(directionsIntent);
                }
            }
        });
    }

    /**
     * This class is an AsyncTask which is used to asynchronously fetch data from the database
     */
    private class NetworkRequest extends AsyncTask<Void, Void, String> {
        String url;
        HashMap<String, String> params;
        HTTPMethod httpMethod;

        /**
         * Constructor for NetworkRequest
         *
         * @param url        The url on which to sent the data to
         * @param params     The parameters (null if none) to send
         * @param httpMethod The type of HTTP request
         */
        NetworkRequest(String url, HashMap<String, String> params, HTTPMethod httpMethod) {
            this.url = url;
            this.params = params;
            this.httpMethod = httpMethod;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String string) {
            super.onPostExecute(string);
            progressBar.setVisibility(GONE);

            // Try create a new JSONObject from the received result string from the database
            try {
                JSONObject object = new JSONObject(string);

                if (!object.getBoolean("error")) {
                    Toast.makeText(getActivity(), object.getString("message"), Toast.LENGTH_SHORT).show();
                    updateAbout(object.getJSONArray("data"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();

            switch (httpMethod) {
                case POST:
                    return requestHandler.sendPostRequest(url, params);
                case GET:
                    return requestHandler.sendGetRequest(url);
                default:
                    return null;
            }
        }
    }
}
