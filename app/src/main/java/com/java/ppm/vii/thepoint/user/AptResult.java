package com.java.ppm.vii.thepoint.user;



import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.java.ppm.vii.thepoint.R;

public class AptResult extends AppCompatActivity
{
    EditText DisplayVenueDetails;

    Intent viwerGetter;

    String a;

    ImageView VenImgDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_display_result);
        //todo: Add Fragment resource linking to here to display Fragment Results pertaining to that specific element derived from the Fragment Array
        getVenueDetails();
    }

    void getVenueDetails()
    {
        //VenueIDResolver

        DisplayVenueDetails = findViewById(R.id.VenueTitle);

        VenImgDisplay = findViewById(R.id.DispView);

        viwerGetter = getIntent();

        //Bundle extra = getIntent().getStringExtra("venueid");



        try
        {
            //a = viwerGetter.getExtras().getString("venueid");
            DisplayVenueDetails.setText(getIntent().getStringExtra("venueid"));
            //VenImgDisplay.setImageResource(getIntent().getIntExtra("imgID", R.id.DispView));

        }
        catch (NullPointerException e)
        {
//            String t = toString(e);
            String NPE = "Fix NPE!";
            Toast.makeText(this, "Fix NPE!"/*toString(e)*/, Toast.LENGTH_LONG).show();
            DisplayVenueDetails.setText(NPE);
        }




//        if(a == null)
//        {
//            Toast.makeText(this, "Fix NPE!", Toast.LENGTH_LONG).show();
//        }
//        else
//        {
//            DisplayVenueDetails.setText(a);
//        }


    }
}
