package com.java.ppm.vii.thepoint.admin;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.java.ppm.vii.thepoint.R;
import com.java.ppm.vii.thepoint.database.API;
import com.java.ppm.vii.thepoint.database.HTTPMethod;
import com.java.ppm.vii.thepoint.database.RequestHandler;
import com.java.ppm.vii.thepoint.database.entity.Event;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Activity for editing of an existing event
 */
public class EditEventActivity extends AppCompatActivity {

    EditText editTextEventId, editTextFormEventTitle, editTextFormEventPromoter, editTextFormEventDate, editTextFormEventDesc, editTextFormEventPrice, editTextFormEventTicketURL, editTextFormEventFbPage;
    ProgressBar progressBar;

    private int eventID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        initLayoutViews();

        Event event = (Event) getIntent().getSerializableExtra("event");
        eventID = event.getId();

        updateForm(event);
    }

    /**
     * Initialise the layout view elements and set listeners, if applicable
     */
    private void initLayoutViews() {
        // Create references to views on the layout
        progressBar = findViewById(R.id.progressBar);

        editTextEventId = findViewById(R.id.editTextEventId);
        editTextFormEventTitle = findViewById(R.id.formEventTitle);
        editTextFormEventPromoter = findViewById(R.id.formEventPromoter);
        editTextFormEventDate = findViewById(R.id.formEventDate);
        editTextFormEventDesc = findViewById(R.id.formEventDesc);
        editTextFormEventPrice = findViewById(R.id.formEventPrice);
        editTextFormEventTicketURL = findViewById(R.id.formEventTicketURL);
        editTextFormEventFbPage = findViewById(R.id.formEventFbPage);

        // Create onClickListener for the cancel button
        Button btnCancel = findViewById(R.id.buttonCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish this activity; returning to the previous activity on the stack
                finish();
            }
        });

        // Create onClickListener for the create button
        Button btnCreate = findViewById(R.id.buttonAddEvent);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the createEvent() method
                createEvent();
                // TODO: Keyboard remains open if 'CREATE EVENT' clicked with keyboard open
            }
        });
    }

    /**
     * Update the form fields to contain the information from the event to be edited
     *
     * @param event The event that is to be edited
     */
    private void updateForm(Event event) {
        if (event != null) {
            editTextFormEventTitle.setText(event.getTitle());
            editTextFormEventPromoter.setText(event.getPromoter());
            editTextFormEventDate.setText(event.getDate());
            editTextFormEventDesc.setText(event.getDescription());
            editTextFormEventPrice.setText(Double.toString(event.getPrice()));
            editTextFormEventTicketURL.setText(event.getTicketUrl());
            editTextFormEventFbPage.setText(event.getFbEvent());
        }
    }

    /**
     * This method fetches the data from the form, checks whether the required fields are present,
     * packs the data into a HashMap and sends a NetworkRequest
     */
    private void createEvent() {
        // Fetch form data
        String title = editTextFormEventTitle.getText().toString().trim();
        String promoter = editTextFormEventPromoter.getText().toString().trim();
        String date = editTextFormEventDate.getText().toString().trim();
        String desc = editTextFormEventDesc.getText().toString().trim();
        String price = editTextFormEventPrice.getText().toString().trim();
        String ticketUrl = editTextFormEventTicketURL.getText().toString().trim();
        String facebookEvent = editTextFormEventFbPage.getText().toString().trim();

        // The following if statements check whether the required fields are present
        if (TextUtils.isEmpty(title)) {
            editTextFormEventTitle.setError("Please enter title");
            editTextFormEventTitle.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(date)) {
            editTextFormEventDate.setError("Please enter date");
            editTextFormEventDate.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(desc)) {
            editTextFormEventDesc.setError("Please enter description");
            editTextFormEventDesc.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(price)) {
            editTextFormEventPrice.setError("Please enter price");
            editTextFormEventPrice.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(price)) {
            editTextFormEventPrice.setError("Please enter price");
            editTextFormEventPrice.requestFocus();
            return;
        }

        // TODO: Validate input as format xxxx.xx
        // TODO: Keep try/catch?
        try {
            if (Double.parseDouble(price) > 1000.00) {
                editTextFormEventPrice.setError("Maximum allowed price is £1000.00");
                editTextFormEventPrice.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            editTextFormEventPrice.setError("Price must be only numbers!");
            editTextFormEventPrice.requestFocus();
            return;
        }

        // Pack the data into a HashMap
        HashMap<String, String> params = new HashMap<>();
        params.put("id", Integer.toString(eventID));
        params.put("title", title);
        params.put("promoter", promoter);
        params.put("date", "2222-02-21 00:00:00"); // TODO: Implement a date/time picker
        params.put("description", desc);
        params.put("price", price);
        params.put("ticket_url", ticketUrl);
        params.put("fb_event", facebookEvent);

        // Send a NetworkRequest with the packed data as a POST operation
        NetworkRequest request = new NetworkRequest(API.UPDATE.getURL(), params, HTTPMethod.POST);
        request.execute();
    }

    /**
     * This class is an AsyncTask which is used to asynchronously fetch data from the database
     */
    private class NetworkRequest extends AsyncTask<Void, Void, String> {
        String url;
        HashMap<String, String> params;
        HTTPMethod httpMethod;

        /**
         * Create an async NetworkRequest to retrieve data in JSON format from the database
         *
         * @param url        The URL to send the query to
         * @param params     The parameters required to perform the query
         * @param httpMethod The type of request to perform (GET or POST)
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
            progressBar.setVisibility(View.GONE);

            // Try create a new JSONObject from the received result string from the database
            try {
                System.out.println("[DEBUG] JSONObject string: =============" + string + "============");
                JSONObject object = new JSONObject(string);

                if (!object.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                    finish();
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
