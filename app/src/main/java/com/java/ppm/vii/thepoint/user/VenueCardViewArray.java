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


    public VenueCardViewArray(String venueId, String setTime, String tags, Uri imgUri, int isGIF)
    {
        VenueId = venueId;
        //VenueTitle =
        SetTime = setTime;
        Tags = tags;
        //ImgID = imgID;
        //iList = IList;
        ImgUri = imgUri;
        IsGIF = isGIF; //This flag is mostly redundant as it did not do much in the end as it did not fix the GIF Lag

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



    public Uri getImgUri() //Image URL can be used here
    {
        return ImgUri;
    }

    public int getIsGIF()
    {
        return IsGIF;
    }
        //Set JSOn Pasre/read here?


    public void setVenueId(String venueId) {
        VenueId = venueId;
    }

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
