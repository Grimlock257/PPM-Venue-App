package com.venue3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import android.widget.Adapter;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main2Activity extends AppCompatActivity
{
    TextView DisplaySearch;

    PopupWindow Show;

    List<VenueImplementable> ListVenue;

    //RecyclerView.Adapter imgLoader;

    RecyclerView RV;

    Adapter imgLoader;

    LinearLayoutManager mgr;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        ListVenue = new ArrayList<>(); //Does this support APNG/GIF/OtherAnimated Media?
//        if ()
//        {
            Random rand = new Random();

            int j = rand.nextInt(16 - 3);

            for(int i = 0; i < j;i++)
            {
                ListVenue.add(new VenueImplementable("VenueEventTitle: "+ i, "EventTime", "" , R.drawable.common_google_signin_btn_icon_dark)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)

            }

            Toast.makeText(this, "RNG: "+ j, Toast.LENGTH_LONG).show();
       // }

        //ListVenue.add(new VenueImplementable("ThisisaVenueEventTitle", "EventTime", "" , "http://goo.gl/gEgYUd"/*Glide.with(imgLoader)*//*getImgid"http://goo.gl/gEgYUd"*/)); //*R.drawable.imgNullErrorPlaceholder*/)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
//        ListVenue.add(new VenueImplementable("VenueEventTitle", "EventTime", "" , R.drawable.common_google_signin_btn_icon_dark)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
//        ListVenue.add(new VenueImplementable("VenueEventTitle2", "EventTime", "" , R.drawable.common_google_signin_btn_icon_disabled)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
//        ListVenue.add(new VenueImplementable("VenueEventTitle", "EventTime", "" , R.drawable.common_google_signin_btn_icon_dark)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
        ListVenue.add(new VenueImplementable("VenueEventTitle2", "EventTime", "" , R.drawable.ic_launcher_background)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
//        ListVenue.add(new VenueImplementable("ThisisaVenueEventTitle2", "EventTime", "" , R.drawable.common_google_signin_btn_text_light)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
//        ListVenue.add(new VenueImplementable("ThisisaVenueEventTitle", "EventTime", "" , R.drawable.common_google_signin_btn_icon_dark)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
//        ListVenue.add(new VenueImplementable("ThisisaVenueEventTitle", "EventTime", "" , R.drawable.common_google_signin_btn_icon_dark)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
//        ListVenue.add(new VenueImplementable("ThisisaVenueEventTitle", "EventTime", "" , R.drawable.common_google_signin_btn_icon_dark)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
//        ListVenue.add(new VenueImplementable("ThisisaVenueEventTitle", "EventTime", "" , R.drawable.common_google_signin_btn_icon_dark)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
//        ListVenue.add(new VenueImplementable("ThisisaVenueEventTitle", "EventTime", "" , R.drawable.common_google_signin_btn_icon_dark)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
//        ListVenue.add(new VenueImplementable("ThisisaVenueEventTitle", "EventTime", "" , R.drawable.common_google_signin_btn_icon_dark)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
//        ListVenue.add(new VenueImplementable("ThisisaVenueEventTitle", "EventTime", "" , R.drawable.common_google_signin_btn_icon_dark)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
//        ListVenue.add(new VenueImplementable("ThisisaVenueEventTitle2", "EventTime", "" , R.drawable.common_google_signin_btn_text_light_focused)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
        //ListVenue.add(new VenueImplementable("ThisisaVenueEventTitle", "EventTime", "" , Glide.with(imgLoader).drawable.common_google_signin_btn_icon_dark)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
        //imgID paramater focable changed from int back to string to test Url Grabber highly properle/Likely to cause/ lead into issues!!!!!!

        //this.RV = (RecyclerView) findViewById(R.id.venue_recycler_view);

        //mgr = new LinearLayoutManager(this);

        //mgr.computeHorizontalScrollOffset()

//        RV.setLayoutManager(mgr);

       // imgLoader

        RecyclerView v = (RecyclerView) findViewById(R.id.venue_recycler_view);

//        imgLoader = new Adapter(ListVenue., this);

        //imgLoader = new Adapter(ListVenue.) //Will this apdptor be heeded/Usefull for the imgLoderHandling for Glide?

        MyItemRecyclerViewAdapter VenueItemAdapter = new MyItemRecyclerViewAdapter(Main2Activity.this, ListVenue/*.getItems*/); //Add ListVenue to the ViewAdaptor



        //GridLayoutManager gridLayoutManager = new GridLayoutManager(Main2Activity.this, 2); //Will This Work?#

        v.setLayoutManager(new GridLayoutManager(this, 3));
        v.setAdapter(VenueItemAdapter);



    }



}
