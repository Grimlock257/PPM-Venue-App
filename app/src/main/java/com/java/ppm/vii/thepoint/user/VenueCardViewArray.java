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
    }

//    public VenueImplementableExt(String venueId, String setTime, String tags, /*String*/ int imgID, String time) { //This Acts alot like an Interface/Constructor but that may be due to List functionality aloow it to act like/as a templete
//        VenueId = venueId;
//        //VenueTitle =
//        SetTime = setTime;
//        Tags = tags;
//        ImgID = imgID; //Must be set to int as it cannot be referenced from the ImageView from the ViewAdaptor as setImageResource must accept int only
//        date = time;
//    }

    public String getVenueId() {
        return VenueId;
    }



    public String getSetTime() {
        return SetTime;
    }

    public String getTags() {
        return Tags;
    }

   /* public *//*String*//*int getImgID() { //Must be Int
        return ImgID;
    }*/

    /*public ArrayList getIList()
    {
        return IList;
    }*/

//    public VenueCardViewArray(int ImgID, ArrayList IList)
//    {
//
//        this.ImgID = ImgID;
//        this.IList = IList;
//    }

    //VImages = findViewById(R.id.venue_cover_Loader);

    public Uri getImgUri() {
        return ImgUri;
    }

    public int getIsGIF()
    {
        return IsGIF;
    }

    //    public String getDate() {
//        return date;
//    }

    /*public String getImgIDtoStringHelper()
    {
        String a = Integer.toString(ImgID);

        return a;
    }*/


    public void setVenueId(String venueId) {
        VenueId = venueId;
    } //Might not need all of these setters and getters/Constructors unless the online grabber can be setup.configured to set the contents fo each element dynamically.

    /*public void setImgID(*//*String*//* int imgID) { //Must be Int
        ImgID = imgID;
    }*/

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
