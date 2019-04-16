package com.java.ppm.vii.thepoint.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.java.ppm.vii.thepoint.R;
import com.java.ppm.vii.thepoint.database.entity.Event;

import java.util.List;

public class CardViewEventAdaptor extends RecyclerView.Adapter<CardViewEventAdaptor.ViewHolder>
{
    private Context mContext;
    private List<VenueCardViewArray> mData;
    private ImageView VImages;
    //private String URL;


    public CardViewEventAdaptor(Context adapterContext, List<VenueCardViewArray> mData) //Fixed bug where List parameter was accidentally set to the old name of mData, mValues, which causes a NPE where getItemCOunt returned as null as the Viewadpator could npt see mData due to this Typo  //Do Not need dummy as we need the VenueImplementable class Structure actually Containing the Venue Data grabed from the Web Side. //DO We Need the Dummy to collect the List array for the GUI?
    {
        this.mContext = adapterContext;  //Adds Context to the List?
        this.mData = mData;
        //this
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View App2view; //?May need to be changed back to appView if issue incurred //Renaming should help distinguish from other views;
        LayoutInflater mInflator = LayoutInflater.from(mContext);
        App2view = mInflator.inflate(R.layout.cardview_user_show_event, parent, false);
        //URL = "http://goo.gl/gEgYUd"; //SHoudl Ideall be done based on the specific id-Uri of the image in question , mayb be worth egtting it on click.
        return new ViewHolder(App2view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder Holder, final int Pos)
    {
        //Item item = mData.get(Pos);
        //Holder currentEvent = mData.get(Pos);

        Holder.VenueViewText.setText(mData.get(Pos).getVenueId());
//        URL = mData.get(Pos).getImgUri();
        //Holder.UriGrabber.hashCode() // May work to add Int identifier
        //Holder.img_Venue.getDrawable(Glide.with(mContext).load(Uri.parse()).into(mData.iterator().));
        //Holder.img_Venue.setImageURI(mData.get(Pos).getImgUri()); //The New Uri DatTup Mya actually work for this
        if (mData.get(Pos).getIsGIF() == 1)
        {
            Glide.with(mContext).asGif().load(mData.get(Pos).getImgUri())/*.placeholder()*/.into(Holder.img_Venue);
            /*Glide.with(mContext).asGif().load(mData.get(Pos).getImgUri())*//*.override(256,256)*//*.into(new Target<GifDrawable>() {
                @Override
                public void onLoadStarted(@Nullable Drawable placeholder) {

                }

                @Override
                public void onLoadFailed(@Nullable Drawable errorDrawable) {

                }

                @Override
                public void onResourceReady(@NonNull GifDrawable resource, @Nullable Transition<? super GifDrawable> transition) {
                    Glide.with(mContext).asGif().load(mData.get(Pos).getImgUri()).into(Holder.img_Venue);
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {

                }

                @Override
                public void getSize(@NonNull SizeReadyCallback cb) {

                }

                @Override
                public void removeCallback(@NonNull SizeReadyCallback cb) {

                }

                @Override
                public void setRequest(@Nullable Request request) {

                }

                @Nullable
                @Override
                public Request getRequest() {
                    return null;
                }

                @Override
                public void onStart() {

                }

                @Override
                public void onStop() {

                }

                @Override
                public void onDestroy() {

                }
            });*/
        }
        else
        {
            Glide.with(mContext).asDrawable().load(mData.get(Pos).getImgUri()).into(Holder.img_Venue);
        }
        //eventViewHolder.eventDate.setText(currentEvent.getDate())
//        Holder.VenueViewText.setText(mData.get(Pos).getId());  //Grabs Venue ID Directly
//        //Holder.mView.get
//        //Holder.
//        Holder.VenueViewText.setText(mData.get(Pos).getDate()); //Grabs Venue img directly, is set to int so may cause issues as the rest of the mData List is Strings not ints
//        Holder.img_Venue.setImageResource(mData.get(Pos).getImgID()); //Grabs Venue img directly, is set to int so may cause issues as the rest of the mData List is Strings not ints
        //Glide.with(mContext).load("http://goo.gl/gEgYUd").into(geti)
        //Glide.with(this.mContext).load(mData.get(Pos)).diskCacheStrategy(DiskCacheStrategy.ALL).into(Holder.img_Venue()); //May be wrong method +Context to prepare image grabber
        //LoadIm

        Holder.VenueCard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent Viwer;
                Viwer = new Intent(mContext, AptResult.class); //Sets Content to this Activity and the Specific elements of the CardviewAdaptor interacted with?
                String venueid = mData.get(Pos).getVenueId();
                Uri ImgUri = mData.get(Pos).getImgUri();
                //String imgid = mData.get(Pos).getImgIDtoStringHelper(); //Hacky Method to get imgID to String may be Problematic?

                //int imgID = mData.get(Pos).getImgID();
                //GlideBuilder i;

                Viwer.putExtra("venueid", venueid);/*mData.get(Pos).getVenueId())*/;
                //Viwer.putExtra("imgID", );
                Viwer.putExtra("ImgLoc", ImgUri/*Holder.UriGrabber*/); //IS this Broken?
                //Viwer.toUri(ImgUri); //IS this Broken?
                v.getContext().startActivity(Viwer);


            }

        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView VenueViewText;
        TextView eventdate;
        //String imgGrabber;
        ImageView img_Venue;
        CardView VenueCard;
        //GlideUrl UriGrabber;
        public ViewHolder(View App2view)
        {
            super(App2view);
            mView = App2view;
            VenueViewText = (TextView) App2view.findViewById(R.id.venue_title);
            //imgGrabber =
            //eventDate = App2view.findViewById(R.id.)
            img_Venue = /*(ImageView)*/ App2view.findViewById(R.id.venue_cover_Loader); //venue_cover_id
            //Glide.with(mContext).load(Uri.parse(URL)).into(img_Venue); //Wrong Context? //Also add Iteratble ArrayList to interate through images later?
            //Glide.with(mContext).load(geti).into(img_Venue); //may be icnorretc location to imploemnt Gldie into the Arraylist img_Venue
            //Glide.with(mContext).load()).into(img_Venue); //may be icnorretc location to imploemnt Gldie into the Arraylist img_Venue

            //mContentView = (TextView) App2view.findViewById(R.id.content);
            VenueCard = (CardView) App2view.findViewById(R.id.VenueCardView);
        }

//        public ImageView getImg_Venue() //Image garrper impelmented via Glide  //Where is this Method Decailred?
//        {
//            return this.img_Venue;
//        }


    }
    @Override
    public int getItemCount() {
        return mData.size();
    }


}
