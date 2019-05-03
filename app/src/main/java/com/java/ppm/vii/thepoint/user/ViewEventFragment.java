package com.java.ppm.vii.thepoint.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.java.ppm.vii.thepoint.R;
import com.java.ppm.vii.thepoint.database.entity.Event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewEventFragment extends Fragment {
    TextView eventPromoter, eventTitle, eventDate, eventTime, eventDescription, eventPrice, eventFBEvent;
    ImageView eventImage;

    private Event event;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_view_event, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        event = (Event) getArguments().getSerializable("event");

        initLayoutViews(view);

        displayEvent();
    }

    /**
     * Initialise the layout view elements and set listeners, if applicable
     */
    private void initLayoutViews(View view) {
        eventImage = view.findViewById(R.id.eventImage);
        eventPromoter = view.findViewById(R.id.eventPromoter);
        eventTitle = view.findViewById(R.id.eventTitle);
        eventDate = view.findViewById(R.id.eventDate);
        eventTime = view.findViewById(R.id.eventTime);
        eventDescription = view.findViewById(R.id.eventDescription);
        eventPrice = view.findViewById(R.id.eventPrice);
        eventFBEvent = view.findViewById(R.id.buttonFBEvent);
    }

    /**
     * Display the selected event to the user
     */
    private void displayEvent() {
        try {
            Uri ImgURI = Uri.parse(event.getMainImage());
            Glide.with(this).load(ImgURI).into(eventImage);

            // If there is a promoter, set the promoter otherwise, set the height of the promoter TextView element to 0 to hide it
            if (!event.getPromoter().equals(""))
                eventPromoter.setText(event.getPromoter() + " presents...");
            else
                eventPromoter.setHeight(0);

            eventTitle.setText(event.getTitle());

            try {
                eventDate.setText(getFormattedDate(event.getDate()));
            } catch (ParseException e) {
                eventDate.setText("Uh oh! Date could not be retrieved!");
            }

            try {
                eventTime.setText(getFormattedTime(event.getDate()));
            } catch (ParseException e) {
                eventTime.setText("Uh oh! Date could not be retrieved!");
            }

            eventPrice.setText(Double.toString(event.getPrice()));
            eventDescription.setText(event.getDescription());

            if (event.getFbEvent() == "") {
                eventFBEvent.setVisibility(View.GONE);
            } else {
                eventFBEvent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri facebook = Uri.parse(event.getFbEvent());
                        Intent facebookIntent = new Intent(Intent.ACTION_VIEW, facebook);

                        // Check if there is an activity to handle this intent, if so, open it
                        if (facebookIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                            startActivity(facebookIntent);
                        }
                    }
                });
            }

            // eventFBEvent.setText(event.getFbEvent()); // TODO OnClickHandler URL
        } catch (NullPointerException e) {
            String error = "Oops! Something went wrong";
            Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
            eventTitle.setText(error);
        }
    }

    /**
     * Format a given time (retrieved from the database) into 12 hour format time, i.e 8:00 PM
     *
     * @param inputTime The input time retrieved from the database
     *
     * @return The formatted time in 12 hour format with AM / PM
     *
     * @throws ParseException If the format provided cannot be parsed
     */
    private String getFormattedTime(String inputTime) throws ParseException {
        // The format retrieved from the database (stored in this format by database software)
        DateFormat inputFormat = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
        Date eventDate = inputFormat.parse(inputTime);

        // Format the input time into the specified format
        DateFormat outputFormat = new SimpleDateFormat("h:mm a");

        return outputFormat.format(eventDate);
    }

    /**
     * Format a given date (retrieved from the database) into the following format: Monday 1st January
     *
     * @param inputDate The input date retrieved from the database
     *
     * @return The formatted date in the format Monday 1st January
     *
     * @throws ParseException If the format provided cannot be parsed
     */
    private String getFormattedDate(String inputDate) throws ParseException {
        // The format retrieved from the database (stored in this format by database software)
        DateFormat inputFormat = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
        Date eventDate = inputFormat.parse(inputDate);

        // Format the input date into the specified format
        DateFormat outputFormat = new SimpleDateFormat("EEEE d'" + getDaySuffix(eventDate.getDate()) + "' MMMM");

        return outputFormat.format(eventDate);
    }

    /**
     * Determine what the suffix (st, nd, rd, th) of the month day should
     *
     * @param n The date of the month
     *
     * @return The suffix to use for the given day of the month
     */
    private String getDaySuffix(final int n) {
        // See if the date is 11th, 12th or 13th as these are exceptions to the naming convention
        if (n >= 11 && n <= 13) {
            return "th";
        }

        // Determine the suffix depending on what the date ends in
        switch (n % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }
}
