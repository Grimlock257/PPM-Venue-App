package com.java.ppm.vii.thepoint.user;

import android.net.Uri;

import com.java.ppm.vii.thepoint.R;

import java.util.ArrayList;

public class VenueCardViewArray
{
    private String VenueId;
    //private String VenueTitle = Find;
    private String SetTime;
    private String Tags;
    //private /*String*/ int ImgID;
    private Uri ImgUri;
    private String date;
    private int IsGIF;

    //private ArrayList IList;


    public VenueCardViewArray(String venueId, String setTime, String tags, Uri imgUri, int isGIF/*,*/ /*String*/ /*int imgID*//*, ArrayList iList*/) { //This Acts alot like an Interface/Constructor but that may be due to List functionality aloow it to act like/as a templete
        VenueId = venueId;
        //VenueTitle =
        SetTime = setTime;
        Tags = tags;
        //ImgID = imgID;
        //iList = IList;
        ImgUri = imgUri;
        IsGIF = isGIF;

        //Must be set to int as it cannot be referenced from the ImageView from the ViewAdaptor as setImageResource must accept int only
        //Correction: SetImage resource can also Accept URI, not just Int which makes it very useful for accepting and Handling URLs for Glide Functionality/Interfacing
    }


    public String getVenueId() {
        return VenueId;
    }



    public String getSetTime() {
        return SetTime;
    }

    public String getTags() {
        return Tags;
    }



    public Uri getImgUri() {
        return ImgUri;
    }

    public int getIsGIF()
    {
        return IsGIF;
    }



    public void setVenueId(String venueId) {
        VenueId = venueId;
    } //Below comment is somewhat incorrect: Actually need these Setters and Getters to allow the ArrayList to be public/ accessible to the other classes
    // Might not need all of these setters and getters/Constructors unless the online grabber can be setup.configured to set the contents fo each element dynamically.

    public void setImgUri(Uri imgUri) {
        ImgUri = imgUri;
    }

    public void setIsGIF(int isGIF) {
        IsGIF = isGIF;
    }

    public void setSetTime(String setTime) {
        SetTime = setTime;
    }

    public void setTags(String tags) {
        Tags = tags;
    }
}
