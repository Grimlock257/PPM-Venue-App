package com.java.ppm.vii.thepoint.user;

public class VenueCardViewArray
{
    private String VenueId;
    //private String VenueTitle = Find;
    private String SetTime;
    private String Tags;
    private /*String*/ int ImgID;
    private String date;



    public VenueCardViewArray(String venueId, String setTime, String tags, /*String*/ int imgID) { //This Acts alot like an Interface/Constructor but that may be due to List functionality aloow it to act like/as a templete
        VenueId = venueId;
        //VenueTitle =
        SetTime = setTime;
        Tags = tags;
        ImgID = imgID; //Must be set to int as it cannot be referenced from the ImageView from the ViewAdaptor as setImageResource must accept int only
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

    public /*String*/int getImgID() { //Must be Int
        return ImgID;
    }

//    public String getDate() {
//        return date;
//    }

    public String getImgIDtoStringHelper()
    {
        String a = Integer.toString(ImgID);

        return a;
    }


    public void setVenueId(String venueId) {
        VenueId = venueId;
    } //Might not need all of these setters and getters/Constructors unless the online grabber can be setup.configured to set the contents fo each element dynamically.

    public void setImgID(/*String*/ int imgID) { //Must be Int
        ImgID = imgID;
    }

    public void setSetTime(String setTime) {
        SetTime = setTime;
    }

    public void setTags(String tags) {
        Tags = tags;
    }
}