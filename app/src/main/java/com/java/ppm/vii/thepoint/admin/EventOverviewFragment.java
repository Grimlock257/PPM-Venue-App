package com.java.ppm.vii.thepoint.admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.java.ppm.vii.thepoint.R;
import com.java.ppm.vii.thepoint.database.API;
import com.java.ppm.vii.thepoint.database.HTTPMethod;
import com.java.ppm.vii.thepoint.database.RequestHandler;
import com.java.ppm.vii.thepoint.database.entity.Event;
import com.java.ppm.vii.thepoint.user.ViewEventActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static android.view.View.GONE;

/**
 * Activity for event overview
 */
public class EventOverviewFragment extends Fragment {

    private ArrayList<Event> eventList;

    EventAdapter adapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_event_overview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initLayoutViews(view);

        fetchEvents();
    }

    /**
     * Initialise the layout view elements and set listeners, if applicable
     *
     * @param view The root view in which view will be located
     */
    private void initLayoutViews(View view) {
        // Create references to views on the layout
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        // Create onClickListener for the create button
        Button btnAddEvent = view.findViewById(R.id.buttonAddEvent);
        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), CreateEventActivity.class);
                startActivity(myIntent);
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
     * Create a NetworkRequest to delete an event with the provided ID from the database
     *
     * @param id The ID of the event to delete
     */
    private void deleteEvent(int id) {
        NetworkRequest request = new NetworkRequest(API.DELETE.getURL() + id, null, HTTPMethod.GET);
        request.execute();
    }

    /**
     * Create a NetworkRequest to retrieve the events from the database
     */
    private void fetchEvents() {
        NetworkRequest request = new NetworkRequest(API.SELECT.getURL(), null, HTTPMethod.GET);
        request.execute();
    }

    /**
     * This method refreshes the RecyclerView for the event list by taking in the new data and
     * updating the RecyclerView
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
     * This method updates the RecyclerView using the using the eventList. Sets onItemClickListeners
     * for buttons on each card
     */
    public void updateRecyclerView() {
        adapter = new EventAdapter(eventList);
        recyclerView.setAdapter(adapter);

        // Create an listener for elements within the card
        adapter.setOnItemClickListener(new EventAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(int position) {
                // Retrieve the clicked event
                Event event = eventList.get(position);

                // Create an intent to open the EditEventActivity, store the clicked event inside the intent
                Intent intentEditEvent = new Intent(getActivity(), EditEventActivity.class);
                intentEditEvent.putExtra("event", event);
                startActivity(intentEditEvent);
            }

            @Override
            public void onDeleteClick(int position) {
                // Retrieve the clicked event
                final Event event = eventList.get(position);

                // Create a new AlertDialog for confirmation
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.dialog);

                builder.setTitle(event.getTitle())
                        .setMessage("Are you sure you want to delete this event? This cannot be undone!")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                deleteEvent(event.getId());
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
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
                System.out.println("[DEBUG] JSONObject string: =============" + string + "============");
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