package com.java.ppm.vii.thepoint.user;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.java.ppm.vii.thepoint.R;
import com.java.ppm.vii.thepoint.database.entity.Event;

public class ViewEventActivity extends AppCompatActivity {
    EditText eventTitle;
    ImageView eventImage;

    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_event);

        event = (Event) getIntent().getSerializableExtra("event");

        initLayoutViews();

        displayEvent();
    }

    /**
     * Initialise the layout view elements and set listeners, if applicable
     */
    private void initLayoutViews() {
        eventTitle = findViewById(R.id.eventTitle);
        eventImage = findViewById(R.id.eventImage);
    }

    /**
     * Display the selected event to the user
     */
    private void displayEvent() {
        try {
            Uri ImgURI = Uri.parse(event.getMainImage());
            eventTitle.setText(event.getTitle());
            Glide.with(this).load(ImgURI).into(eventImage);
        } catch (NullPointerException e) {
            String error = "Oops! Something went wrong";
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
            eventTitle.setText(error);
        }
    }
}
