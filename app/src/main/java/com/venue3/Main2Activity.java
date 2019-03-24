package com.venue3;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;

import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.Toast;

import android.app.Fragment;

import java.util.ArrayList;
import java.util.List;
//import android.app.FragmentTransaction;
//import android.content.res.Configuration;
//import android.os.Bundle;


public class Main2Activity extends AppCompatActivity
{
    Button Button2;

    TextView DisplaySearch;

    PopupWindow Show;

    List<VenueImplementable> ListVenue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        Intent intent = getIntent();
//        //String value = intent.getStringExtra("Key");
//        String getSearch = intent.getStringExtra("InputQuery");
//        DisplaySearch = (TextView) findViewById(R.id.DisplaySearch);  //Could Getup Search to also use an offline logged AufillNots system to help aid Dyamic Venue Searching, but may require Caching Database Contents Over time.
//        DisplaySearch.setText(getSearch);

        //Toast.makeText(getApplicationContext(), "String: " + getSearch, Toast.LENGTH_LONG).show();

//        if(getSearch == null)
//        {
//            //Show.getContentView()R.id.BarSearch
//            Toast.makeText(getApplicationContext(), getString(R.string.SearchFail), Toast.LENGTH_SHORT).show();
//            DisplaySearch.setText(getString(R.string.SearchFail));
//        }

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
