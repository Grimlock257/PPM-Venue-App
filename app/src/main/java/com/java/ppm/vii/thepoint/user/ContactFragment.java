package com.java.ppm.vii.thepoint.user;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.java.ppm.vii.thepoint.R;
import com.java.ppm.vii.thepoint.admin.EditEventActivity;
import com.java.ppm.vii.thepoint.database.API;
import com.java.ppm.vii.thepoint.database.HTTPMethod;
import com.java.ppm.vii.thepoint.database.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ContactFragment extends Fragment {
    TextView contactName, contactEmail, contactSubject, contactMessage;
    ProgressBar progressBar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initLayoutViews(view);
    }

    /**
     * Initialise the layout view elements and set listeners, if applicable
     */
    private void initLayoutViews(View view) {
        // Create references to views on the layout
        progressBar = view.findViewById(R.id.progressBar);

        contactName = view.findViewById(R.id.formContactName);
        contactEmail = view.findViewById(R.id.formContactEmail);
        contactSubject = view.findViewById(R.id.formContactSubject);
        contactMessage = view.findViewById(R.id.formContactMessage);

        // Create onClickListener for the create button
        Button btnSend = view.findViewById(R.id.buttonSendMessage);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    /**
     * This method fetches the data from the form, checks whether the required fields are present,
     * packs the data into a HashMap and sends a NetworkRequest
     */
    private void sendMessage() {
        // Fetch form data
        String name = contactName.getText().toString().trim();
        String email = contactEmail.getText().toString().trim();
        String subject = contactSubject.getText().toString().trim();
        String message = contactMessage.getText().toString().trim();

        // The following if statements check whether the required fields are present
        if (TextUtils.isEmpty(name)) {
            contactName.setError("Please enter name");
            contactName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            contactEmail.setError("Please enter email");
            contactEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(subject)) {
            contactSubject.setError("Please enter subject");
            contactSubject.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(message)) {
            contactMessage.setError("Please enter message");
            contactMessage.requestFocus();
            return;
        }

        // Pack the data into a HashMap
        HashMap<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("subject", subject);
        params.put("message", message);

        // Send a NetworkRequest with the packed data as a POST operation
        NetworkRequest request = new NetworkRequest(API.CONTACT.getURL(), params, HTTPMethod.POST);
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
                    Toast.makeText(getActivity(), object.getString("message"), Toast.LENGTH_SHORT).show();
                    getActivity().onBackPressed();
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
