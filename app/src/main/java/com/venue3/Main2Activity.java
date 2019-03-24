package com.venue3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;


import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Main2Activity extends AppCompatActivity
{
    TextView DisplaySearch;

    PopupWindow Show;

    List<VenueImplementable> ListVenue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        ListVenue = new ArrayList<>();

        ListVenue.add(new VenueImplementable("ThisisaVenueEventTitle", "EventTime", "" , R.drawable.common_google_signin_btn_icon_light_normal /*R.drawable.imgNullErrorPlaceholder*/)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
        ListVenue.add(new VenueImplementable("ThisisaVenueEventTitle", "EventTime", "" , R.drawable.common_google_signin_btn_icon_light_normal /*R.drawable.imgNullErrorPlaceholder*/)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
        ListVenue.add(new VenueImplementable("ThisisaVenueEventTitle", "EventTime", "" , R.drawable.common_google_signin_btn_icon_light_normal /*R.drawable.imgNullErrorPlaceholder*/)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
        ListVenue.add(new VenueImplementable("ThisisaVenueEventTitle", "EventTime", "" , R.drawable.common_google_signin_btn_icon_light_normal /*R.drawable.imgNullErrorPlaceholder*/)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
        ListVenue.add(new VenueImplementable("ThisisaVenueEventTitle", "EventTime", "" , R.drawable.common_google_signin_btn_icon_light_normal /*R.drawable.imgNullErrorPlaceholder*/)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
        ListVenue.add(new VenueImplementable("ThisisaVenueEventTitle", "EventTime", "" , R.drawable.common_google_signin_btn_icon_light_normal /*R.drawable.imgNullErrorPlaceholder*/)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
        ListVenue.add(new VenueImplementable("ThisisaVenueEventTitle", "EventTime", "" , R.drawable.common_google_signin_btn_icon_light_normal /*R.drawable.imgNullErrorPlaceholder*/)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)

        RecyclerView v = (RecyclerView) findViewById(R.id.venue_recycler_view);

        MyItemRecyclerViewAdapter VenueItemAdapter = new MyItemRecyclerViewAdapter(Main2Activity.this, ListVenue);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Main2Activity.this, 2); //Will This Work?#

        v.setLayoutManager(new GridLayoutManager(this, 3));
        v.setAdapter(VenueItemAdapter);


    }



}
