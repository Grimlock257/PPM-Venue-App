package com.java.ppm.vii.thepoint.user;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.java.ppm.vii.thepoint.R;
import com.java.ppm.vii.thepoint.database.API;
import com.java.ppm.vii.thepoint.database.HTTPMethod;
import com.java.ppm.vii.thepoint.database.RequestHandler;
import com.java.ppm.vii.thepoint.database.entity.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static android.view.View.GONE;

/**
 * ToDo: Add Database Synchronisation to allow the Gridlayout to be updated based on Current database Contents
 * Also need to add Gridlayout GUI correction to fix the Wonky Margins as well as to programmatically adjust based on Phone resolution and Aspect Ratio
 * Add Search and tagging system if time permitting
 * Animation Handler for loading. Rotating Tiles?
 * Swipe to Left to add tiles to the User accounts preferences/Saved/Followed Venues?
 * Worth bothering to fix the Gif Bug with laggy Encoding?
 */

public class EventOverviewFragment extends Fragment {

    ArrayList<Event> eventList;
    EditText searchField;
    RecyclerView recyclerView;
    EventAdapter adapter; // TODO: Local variable?
    ProgressBar progressBar;
    GridLayoutManager layoutManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_event_overview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initLayoutViews(view);

        // TODO: Issue #1 on GitHub. Causes NPE when no data or device rotated
        initSearch();

        fetchEvents();
    }

    /**
     * Initialise the layout view elements and set listeners, if applicable
     */
    private void initLayoutViews(View view) {
        // Create references to views on the layout
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.venue_recycler_view);
        searchField = view.findViewById(R.id.searchField);
        layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);
    }

    /**
     * Initialise the search input text field and create a listener to handle input
     */
    private void initSearch() {
        searchField.setSingleLine();
        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    adapter.getFilter().filter(s);
                } catch (NullPointerException e) {
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        // When this activity is resumed, update the event RecyclerView
        fetchEvents();
    }

    /**
     * Create a NetworkRequest to retrieve the events from the database
     */
    private void fetchEvents() {
        NetworkRequest request = new NetworkRequest(API.SELECT.getURL(), null, HTTPMethod.GET);
        request.execute();
    }

    /**
     * This method refreshes the CardView for the event list by taking in the new data and
     * updating the CardView
     *
     * @param events The JSONArray of events (as this data is returned from the database)
     *
     * @throws JSONException If the JSONArray is malformed or missing keys
     */
    private void updateEventList(JSONArray events) throws JSONException {
        eventList = new ArrayList<>();

        // Fetch all the events from the JSONArray and convert them to Event objects
        // and store them into the ArrayList
        for (int i = 0; i < events.length(); i++) {
            JSONObject obj = events.getJSONObject(i);

            eventList.add(new Event(
                    obj.getInt("id"),
                    obj.getString("promoter"),
                    obj.getString("title"),
                    obj.getString("date"),
                    obj.getString("description"),
                    obj.getDouble("price"),
                    obj.getString("ticket_url"),
                    obj.getString("fb_event"),
                    obj.getString("main_image"),
                    obj.getInt("active")
            ));
        }

        // Update the RecyclerView to reflect the new eventList
        updateRecyclerView();
    }

    /**
     * This method updates the RecyclerView using the using the eventList.
     */
    public void updateRecyclerView() {
        adapter = new EventAdapter(eventList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new EventAdapter.OnItemClickListener() {
            @Override
            public void onEventCardClick(Event event) {
                // Create an intent to open the ViewEventFragment, store the clicked event inside the intent
                // Intent intentViewEvent = new Intent(getActivity(), ViewEventFragment.class);
                // intentViewEvent.putExtra("event", event);
                // startActivity(intentViewEvent);

                Bundle bundle = new Bundle();
                bundle.putSerializable("event", event);

                Fragment viewEventFragment = new ViewEventFragment();
                viewEventFragment.setArguments(bundle);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, viewEventFragment);
                transaction.addToBackStack(null);
                transaction.commit();
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
                    updateEventList(object.getJSONArray("data"));
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
