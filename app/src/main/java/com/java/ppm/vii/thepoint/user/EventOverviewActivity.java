package com.java.ppm.vii.thepoint.user;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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
import static java.lang.Math.floor;

/**
 * ToDo: Add Database Synchronisation to allow the Gridlayout to be updated based on Current database Contents
 * Also need to add Gridlayout GUI correction to fix the Wonky Margins as well as to programmatically adjust based on Phone resolution and Aspect Ratio
 * Add Search and tagging system if time permitting
 * Animation Handler for loading. Rotating Tiles?
 * Swipe to Left to add tiles to the User accounts preferences/Saved/Followed Venues?
 * Worth bothering to fix the Gif Bug with laggy Encoding?
 */

public class EventOverviewActivity extends AppCompatActivity {

    ArrayList<Event> eventList;
    RecyclerView recyclerView;
    EventAdapter adapter; // TODO: Local variable?
    ProgressBar progressBar;
    GridLayoutManager layoutManager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_event_overview);

        initLayoutViews();

        initSearch();

        fetchEvents();
    }

    /**
     * Initialise the layout view elements and set listeners, if applicable
     */
    private void initLayoutViews() {
        // Create references to views on the layout
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.venue_recycler_view);
        layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.getLayoutParams().width = (int) floor((getResources().getDisplayMetrics().widthPixels) / 1.05f); //This allows a margin to be set around the tiles with any resolution
    }

    /**
     * Initialise the search input text field and create a listener to handle input
     */
    private void initSearch() {
        EditText DisplayQuery = findViewById(R.id.DisplayQuery);
        DisplayQuery.setSingleLine();
        DisplayQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
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
                System.out.println("[DEBUG] JSONObject string: =============" + string + "============");
                JSONObject object = new JSONObject(string);

                if (!object.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
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
