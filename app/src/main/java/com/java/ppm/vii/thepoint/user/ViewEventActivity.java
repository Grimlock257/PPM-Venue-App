package com.java.ppm.vii.thepoint.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.java.ppm.vii.thepoint.R;
import com.java.ppm.vii.thepoint.database.entity.Event;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Activity for viewing a particular event
 */
public class ViewEventActivity extends AppCompatActivity {

    ArrayList<Event> llist;

    ArrayList<VenueCardViewArray> List;

    RecyclerView v;

    LinearLayoutManager mgr;
    JSONArray events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_event);

        //updateEventList(llist);

        llist = new ArrayList<>();

        try
        {

            for (int i = 0; i < events.length(); i++) {
                JSONObject obj = events.getJSONObject(i);

                llist.add(new Event(
                        obj.getInt("id"),
                        obj.getString("promoter"),
                        obj.getString("title"),
                        obj.getString("date"),
                        obj.getString("description"),
                        obj.getDouble("price"),
                        obj.getString("ticket_url"),
                        obj.getString("fb_event"),
                        obj.getString("main_image"),
                        obj.getInt("active")
                ));
            }
        }

        catch (NullPointerException a)
        {
            Toast.makeText(this, "NPE!, Failed to get JSONArraySize!", Toast.LENGTH_LONG).show();

            //ArrayList<VenueCardViewArray> List;

            List = new ArrayList<>();
            Random rand = new Random();

            int j = rand.nextInt(16 - 3);

            for(int i = 0; i < j;i++)
            {

                List.add(new VenueCardViewArray("VenueEventTitle: " + i, "EventTime", "", R.drawable.ic_launcher_foreground)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
            }
            v = findViewById(R.id.venue_recycler_view);

//        imgLoader = new Adapter(ListVenue., this);

            //imgLoader = new Adapter(ListVenue.) //Will this apdptor be heeded/Usefull for the imgLoderHandling for Glide?

            CardViewEventAdaptor VenueItemAdapter = new CardViewEventAdaptor(ViewEventActivity.this, List/*.getItems*/); //Add ListVenue to the ViewAdaptor



            //GridLayoutManager gridLayoutManager = new GridLayoutManager(ViewEventActivity.this, 3); //Will This Work?#
            mgr = new LinearLayoutManager(this);
            v.setLayoutManager(new GridLayoutManager(this, 3));
            v.setAdapter(VenueItemAdapter);
        }
        catch (JSONException e)
        {
            Toast.makeText(this, "Failed to Read JSON ", Toast.LENGTH_LONG).show();
            //System.err.println();
        }





    }

//
}
