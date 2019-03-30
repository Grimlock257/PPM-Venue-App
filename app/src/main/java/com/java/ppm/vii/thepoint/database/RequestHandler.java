package com.java.ppm.vii.thepoint.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class RequestHandler {

    /**
     * Send httpPostRequest to the server
     *
     * @param requestURL The URL for which to send the httpPostRequest
     * @param params     The parameters to put in the post header as a HashMap of [name, value]
     */
    public String sendPostRequest(String requestURL, HashMap<String, String> params) {
        // StringBuilder to store the response from the remote server
        StringBuilder sb = new StringBuilder();

        try {
            // Create a HTTP connection to the URL
            URL url = new URL(requestURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // Set attributes for the connection
            con.setReadTimeout(15000);
            con.setConnectTimeout(15000);
            con.setRequestMethod("POST");
            con.setDoInput(true);
            con.setDoOutput(true);

            // Create an OutputStream to be able to send data to the connection
            OutputStream os = con.getOutputStream();

            // Write the POST parameters to the output stream
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getParamsAsString(params));

            // Close the BufferedWriter and OutputStream
            writer.flush();
            writer.close();
            os.close();

            // If the response code was an HTTP_OK (i.e the a successful request), add the response to the
            // StringBuilder object that the method returns
            int responseCode = con.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                // Create a BufferedReader to read the response back from the server
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                // For as many lines received, add them to the response string
                String response;
                while ((response = br.readLine()) != null) {
                    sb.append(response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    /**
     * Send a GET request to the specified URL
     *
     * @param requestURL The URL on the remote server for which to send the remote request
     *
     * @return
     */
    public String sendGetRequest(String requestURL) {
        // StringBuilder to store the response from the remote server
        StringBuilder sb = new StringBuilder();

        try {
            // Create a HTTP connection to the URL
            URL url = new URL(requestURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // Create a BufferedReader to read the response back from the server
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            // For as many lines received, add them to the response string
            String response;
            while ((response = br.readLine()) != null) {
                sb.append(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    /**
     * Convert the HashMap<String, String> to a string
     *
     * @param params The HashMap params to convert to string
     *
     * @return The params as a string format
     *
     * @throws UnsupportedEncodingException Error during encoding
     */
    private String getParamsAsString(HashMap<String, String> params) throws UnsupportedEncodingException {
        // StringBuilder to store the response data field from the remote server
        StringBuilder result = new StringBuilder();

        // From the params HashMap, output them in the form key=value&key=value etc.
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first) {
                first = false;
            } else {
                result.append("&");
            }

            // Append the UTF-8 encoded key=value pair to the result StringBuilder
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}