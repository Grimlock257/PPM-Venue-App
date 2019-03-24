package com.venue3;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private List<VenueImplementable> mData;

    public MyItemRecyclerViewAdapter(Context mContext, List<VenueImplementable> mData) //Fixed bug where List parameter was accidentally set to the old name of mData, mValues, which causes a NPE where getItemCOunt returned as null as the Viewadpator could npt see mData due to this Typo  //Do Not need dummy as we need the VenueImplementable class Structure actually Containing the Venue Data grabed from the Web Side. //DO We Need the Dummy to collect the List array for the GUI?
    {
        this.mContext = mContext;  //Adds Context to the List?
        this.mData = mData;
    }


    //@NonNull //Needs to be null as Failsafe in case Venue resource Grab pertaining to Specific/Targeted ID Fails to prevent issues (May or May Not Be a NullPointer/Innovation Exception)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View Appview;  //Renaming should help distinguish from other views;
        LayoutInflater mInflator = LayoutInflater.from(mContext);
        Appview = mInflator.inflate(R.layout.venue_cardview, parent, false);
        return new ViewHolder(Appview);
    }

    @Override
    public void onBindViewHolder(ViewHolder Holder, final int Pos) {
        Holder.VenueViewText.setText(mData.get(Pos).getVenueId());  //Grabs Venue ID Directly
        Holder.img_Venue.setImageResource(mData.get(Pos).getImgID()); //Grabs Venue img directly, is set to int so may cause issues as the rest of the mData List is Strings not ints


        Holder.VenueCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Viwer;
                Viwer = new Intent(mContext, AptResult.class); //Sets Content to this Activity and the Specific elements of the CardviewAdaptor interacted with?
                Viwer.putExtra("venueid", mData.get(Pos).getVenueId());
                v.getContext().startActivity(Viwer);

            }

        });
    }


    @Override
    public int getItemCount()  //Get Length of Returned Items for the ViewAdaptor to prepare the UI
    {
        return mData.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView VenueViewText;
        ImageView img_Venue;
        CardView VenueCard;

        public ViewHolder(View App2view) {
            super(App2view);
            mView = App2view;
            VenueViewText = (TextView) App2view.findViewById(R.id.venue_title);
            img_Venue = (ImageView) App2view.findViewById(R.id.venue_cover_id);
            //mContentView = (TextView) App2view.findViewById(R.id.content);
            VenueCard = (CardView) App2view.findViewById(R.id.VenueCardView);
        }


    }
}