package com.java.ppm.vii.thepoint.user;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.java.ppm.vii.thepoint.R;
import com.java.ppm.vii.thepoint.database.entity.Event;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;


/**ToDo: Add Database Synchronisation to allow the Gridlayout to be updated based on Current database Contents
  * Also need to add Gridlayout GUI correction to fix the Wonky Margins as well as to programmically adjust based on Phone resolution and Aspect Ratio
  * Add Search and tagging system if time permitting
  * Animation Handler for loading. Rotating Tiles?
  * Swipe to Left to add tiles to the User accounts preferences/Saved/Followed Venues?
  * Worth bothering to fix the Gif Bug with laggy Encoding?
 *
 */



public class ViewEventActivity extends AppCompatActivity {

    //AndroidManifestApplication_largeHeap = true; //May need this if app exceeds default 64Mb Heap

    ArrayList<Event> llist;

    ArrayList<VenueCardViewArray> List;

    RecyclerView GridView;

    LinearLayoutManager mgr;
    JSONArray events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_event);

        //updateEventList(llist);

        llist = new ArrayList<>();

        try //Database Integration Needs to be Fixed
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






            List = new ArrayList<>();
            Random rand = new Random();

            int j = rand.nextInt(16 - 3);  //RNG Generator to demonstrate the Non_Static elements fo the GridLayout
            int i;
            for(i = 0; i < j;i++)
            {

                List.add(new VenueCardViewArray("VenueEventTitle: " + i, "EventTime", "", Uri.parse("http://goo.gl/gEgYUd"), 0/*, R.drawable.ic_launcher_foreground*/)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
            }
            //List.add(new VenueCardViewArray("VenueEventTitle: " + i, "EventTime", "", IList.get(1))); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
            //List.add(new VenueCardViewArray("VenueEventTitle: " + i, "EventTime", "", Uri.parse("https://cdn2.iconfinder.com/data/icons/micon-social-pack/512/playstore-512.png")/*, R.drawable.ic_launcher_foreground*/)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
            List.add(new VenueCardViewArray("VenueEventTitle: " + i, "EventTime", "", Uri.parse("https://steamuserimages-a.akamaihd.net/ugc/945082312477061860/C5FDB883ADB442A30401D78428AFA557CE6E5A1F/?imw=481&imh=506&ima=fit&impolicy=Letterbox&imcolor=%23000000&letterbox=true"), 1/*, R.drawable.ic_launcher_foreground*/)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
            List.add(new VenueCardViewArray("VenueEventTitle: " + i, "EventTime", "", Uri.parse("https://www.artpeoplegallery.com/wp-content/uploads/2015/09/florian-3.gif"), 1/*, R.drawable.ic_launcher_foreground*/)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
            List.add(new VenueCardViewArray("VenueEventTitle: " + i, "EventTime", "", Uri.parse("https://66.media.tumblr.com/155e785eea7dbe19a0b5e6f81c6c3097/tumblr_mzwwaewTU41s2mpexo1_400.gif"), 1/*, R.drawable.ic_launcher_foreground*/)); //Add a Null Check to ensure that if the Arrylist<img> returnsnull that a text or image placeholder is used to avoid a potential nullpointer)
           //Uri GifUri = Glide.with(this).asGif().load()

            GridView = findViewById(R.id.venue_recycler_view);



            CardViewEventAdaptor VenueItemAdapter = new CardViewEventAdaptor(ViewEventActivity.this, List/*.getItems*/); //Add ListVenue to the ViewAdaptor



            //GridLayoutManager gridLayoutManager = new GridLayoutManager(ViewEventActivity.this, 3); //Will This Work?#
            mgr = new LinearLayoutManager(this);
            GridView.setLayoutManager(new GridLayoutManager(this, 3));
            GridView.setAdapter(VenueItemAdapter);
        }
        catch (JSONException e)
        {
            Toast.makeText(this, "Failed to Read JSON ", Toast.LENGTH_LONG).show();
            //System.err.println();
        }





    }

//
}
