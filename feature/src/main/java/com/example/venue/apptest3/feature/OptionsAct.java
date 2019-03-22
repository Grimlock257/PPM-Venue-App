package com.example.venue.apptest3.feature;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OptionsAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Boolean ActBar = getIntent().getBooleanExtra("SetActivityBar", true);
        if(ActBar)
        {
            getSupportActionBar().hide();
        }
        
//        test = ((test) new MainActivity.Shared);
       //boolean test = MainActivity.ApplyOptionSettings.class.
        //ac.test;
       //boolean test = new MainActivity.ApplyOptionSettings.  //How to access current User Settings from Another Activity?

    }
}
