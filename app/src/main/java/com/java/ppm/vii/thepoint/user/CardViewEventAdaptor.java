package com.java.ppm.vii.thepoint.user;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.java.ppm.vii.thepoint.R;
import com.java.ppm.vii.thepoint.database.entity.Event;

import java.util.List;

public class CardViewEventAdaptor extends RecyclerView.Adapter<CardViewEventAdaptor.ViewHolder>
{
    private Context mContext;
    private List<VenueCardViewArray> mData;
//    TextView eventTitle;
//    TextView eventDate;
    // private List<String>

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
        return new ViewHolder(App2view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder Holder, final int Pos) {
        //Item item = mData.get(Pos);
        //Holder currentEvent = mData.get(Pos);

        Holder.VenueViewText.setText(mData.get(Pos).getVenueId());
        //eventViewHolder.eventDate.setText(currentEvent.getDate())
//        Holder.VenueViewText.setText(mData.get(Pos).getId());  //Grabs Venue ID Directly
//        //Holder.mView.get
//        //Holder.
//        Holder.VenueViewText.setText(mData.get(Pos).getDate()); //Grabs Venue img directly, is set to int so may cause issues as the rest of the mData List is Strings not ints
//        Holder.img_Venue.setImageResource(mData.get(Pos).getImgID()); //Grabs Venue img directly, is set to int so may cause issues as the rest of the mData List is Strings not ints
        //Glide.with(mContext).load("http://goo.gl/gEgYUd").into(geti)
        //Glide.with(this.mContext).load(mData.get(Pos)).diskCacheStrategy(DiskCacheStrategy.ALL).into(Holder.img_Venue()); //May be wrong method +Context to prepare image grabber


        Holder.VenueCard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent Viwer;
                Viwer = new Intent(mContext, AptResult.class); //Sets Content to this Activity and the Specific elements of the CardviewAdaptor interacted with?
                String venueid = mData.get(Pos).getVenueId();
                //String imgid = mData.get(Pos).getImgIDtoStringHelper(); //Hacky Method to get imgID to String may be Problematic?
                int imgID = mData.get(Pos).getImgID();

                Viwer.putExtra("venueid", venueid);/*mData.get(Pos).getVenueId())*/;
                Viwer.putExtra("imgID", imgID);
                v.getContext().startActivity(Viwer);


            }

        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView VenueViewText;
        TextView eventdate;
        String imgGrabber;
        ImageView img_Venue;
        CardView VenueCard;

        public ViewHolder(View App2view)
        {
            super(App2view);
            mView = App2view;
            VenueViewText = (TextView) App2view.findViewById(R.id.venue_title);
            //imgGrabber =
            //eventDate = App2view.findViewById(R.id.)
            img_Venue = /*(ImageView)*/ App2view.findViewById(R.id.venue_cover_Loader); //venue_cover_id
            //Glide.with(mContext).load(geti).into(img_Venue); //may be icnorretc location to imploemnt Gldie into the Arraylist img_Venue

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