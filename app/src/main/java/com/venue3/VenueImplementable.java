package com.venue3;

import android.content.Context;

import java.util.List;

public class VenueImplementable
{
    private String VenueId;
    //private String VenueTitle = Find;
    private String SetTime;
    private String Tags;
    private int ImgID;

    public VenueImplementable() {
    }

    public VenueImplementable(String venueId, String setTime, String tags,int imgID) { //This Acts alot like an Interface/Constructor but that may be due to List functionality aloow it to act like/as a templete
        VenueId = venueId;
        //VenueTitle =
        SetTime = setTime;
        Tags = tags;
        ImgID = imgID; //Must be set to int as t cannot be referenced from teh ImageView from teh ViewAdaptor as setImageResoruce must accept int only
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

    public int getImgID() { //Must be Int
        return ImgID;
    }


    public void setVenueId(String venueId) {
        VenueId = venueId;
    } //Might not need all of tehse setters and getters/Consurtctors unelss the online grabber can be setup.configured to set teh ciontents fo each eleklenmt dynamically.

    public void setImgID(int imgID) { //Must be Int
        ImgID = imgID;
    }

    public void setSetTime(String setTime) {
        SetTime = setTime;
    }

    public void setTags(String tags) {
        Tags = tags;
    }
}
