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
import com.java.ppm.vii.thepoint.database.API;
import com.java.ppm.vii.thepoint.database.entity.Event;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewEventFragment extends Fragment {
    TextView eventPromoter, eventTitle, eventDate, eventTime, eventDescription, eventPrice, eventFBEvent, eventGoogleCalURL;
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
        eventGoogleCalURL = view.findViewById(R.id.buttonGoogleCal);
    }

    /**
     * Display the selected event to the user
     */
    private void displayEvent() {
        try {
            Uri ImgURI = Uri.parse(API.getEventImageURL() + event.getMainImage());
            Glide.with(this).load(ImgURI).into(eventImage);

            // If there is a promoter, set the promoter otherwise, set the height of the promoter TextView element to 0 to hide it
            if (!event.getPromoter().equals(""))
                eventPromoter.setText(event.getPromoter() + " presents...");
            else
                eventPromoter.setHeight(0);

            eventTitle.setText(event.getTitle());
            eventDate.setText(getFormattedDate(event.getDate()));
            eventTime.setText(getFormattedTime(event.getDate()));

            String eventPriceStr = Double.toString(event.getPrice());
            if (eventPriceStr.toLowerCase().equals("0.0")) {
                eventPrice.setText("FREE!");
            } else {
                System.out.println("############### DEBUG: " + String.format("%.2f", event.getPrice()));
                eventPrice.setText("£" + String.format("%.2f", event.getPrice()));
            }

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

            eventGoogleCalURL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri googleCalendar = Uri.parse(generateGoogleCalendarURL(event.getTitle(), event.getDate(), event.getDescription()));
                    Intent googleCalendarIntent = new Intent(Intent.ACTION_VIEW, googleCalendar);

                    // Check if there is an activity to handle this intent, if so, open it
                    if (googleCalendarIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(googleCalendarIntent);
                    }
                }
            });
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
     */
    private String getFormattedTime(String inputTime) {
        // The format retrieved from the database (stored in this format by database software)
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date eventDate;

        try {
            eventDate = inputFormat.parse(inputTime);
        } catch (ParseException e) {
            return "Uh oh! Date could not be retrieved!";
        }

        // Format the input time into the specified format
        DateFormat outputFormat = new SimpleDateFormat("h:mm a");

        return outputFormat.format(eventDate);
    }

    /**
     * Format a given time (retrieved from the database) into suitable format for Google Calendar
     *
     * @param inputTime The input time retrieved from the database
     *
     * @return The formatted time for Google Calendar
     */
    private String getFormattedCalendarTime(String inputTime) {
        // The format retrieved from the database (stored in this format by database software)
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date eventDate;

        try {
            eventDate = inputFormat.parse(inputTime);
        } catch (ParseException e) {
            return "Uh oh! Date could not be retrieved!";
        }

        // Format the input time into the specified format
        DateFormat outputFormat = new SimpleDateFormat("YYYYMMd'T'HHmmss/YYYYMMd'T'HHmmss");

        return outputFormat.format(eventDate);
    }

    /**
     * Format a given date (retrieved from the database) into the following format: Monday 1st January
     *
     * @param inputDate The input date retrieved from the database
     *
     * @return The formatted date in the format Monday 1st January
     */
    private String getFormattedDate(String inputDate) {
        // The format retrieved from the database (stored in this format by database software)
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date eventDate;

        try {
            eventDate = inputFormat.parse(inputDate);
        } catch (ParseException e) {
            return "Uh oh! Date could not be retrieved!";
        }

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

    /**
     * Generate a suitable URL to create a new Google Calendar event
     *
     * @param title       The title of the event
     * @param date        The date of the event
     * @param description The description for the event
     *
     * @return The fomatted URL
     *
     * @throws UnsupportedEncodingException If the encoding format is not supported on the device
     */
    private String generateGoogleCalendarURL(String title, String date, String description) {
        try {
            String encodedTitle = URLEncoder.encode(title, "UTF-8");
            String encodedLocation = URLEncoder.encode("NTSU - The Point, Clifton, Nottingham NG11 8NS", "UTF-8");
            String encodedDescription = URLEncoder.encode(description, "UTF-8");

            return "http://www.google.com/calendar/render?action=TEMPLATE" +
                    "&text=" + encodedTitle +
                    "&dates=" + getFormattedCalendarTime(date) +
                    "&location=" + encodedLocation +
                    "&trp=false&details=" + encodedDescription;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
