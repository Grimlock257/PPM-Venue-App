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


import com.venue3.AptResult;
import com.venue3.VenueImplementable;

import java.util.List;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private List<VenueImplementable> mData;

    public MyItemRecyclerViewAdapter(Context mContext, List<VenueImplementable> mData) //Fixed bug where List paramter was accoidentall set to the old name of mData, mValues, which causes a NPE where getItemCOunt returned as null as the Viewadpator could npt see mData due to this Typo  //Do Not need dummy as we need the VenueImplementable class Structure actually Containing the Venue Data grabed from the Web Side. //DO We Need the Dummy to collect the List array for the GUI?
    {
        this.mContext = mContext;  //Adds Context to the List?
        this.mData = mData;
    }


    //@NonNull //Needs to be null as Failsafe in case Venue resource Grab pertaining to Specific/Targeted ID Fails to prevent issues (May or May Not Be a NullPointer/Innovation Exception)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //        View view = LayoutInflater.from(parent.getContext())
        //                .inflate(R.layout.fragment_item, parent, false);
        //        return new ViewHolder(view);
        View Appview;  //Ranaming shoudl help ditinguish from other views;
        LayoutInflater mInflator = LayoutInflater.from(mContext);
        Appview = mInflator.inflate(R.layout.venue_cardview, parent, false);
        return new ViewHolder(Appview);
    }

    @Override
    public void onBindViewHolder(ViewHolder Holder, final int Pos) {
        Holder.VenueViewText.setText(mData.get(Pos).getVenueId());  //Grabs Venue ID Directly
        Holder.img_Venue.setImageResource(mData.get(Pos).getImgID()); //Grabs Venue img dierctly, is set to int so may ause sisues as the rest of the mData List is Strings not ints


        Holder.VenueCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Viwer;
                Viwer = new Intent(mContext, AptResult.class); //Sets Content to this Activity and the SPeiciv elelemts of the CardviewAdpator inetracted with?
                Viwer.putExtra("venueid", mData.get(Pos).getVenueId());
                v.getContext().startActivity(Viwer);

            }

        });
    }


    @Override
    public int getItemCount()  //Get Lnegth of Returns Items for the ViewAdaptor to prepare the UI
    {
        return mData.size();
    }


//    private final OnListFragmentInteractionListener mListener;
//
//    public MyItemRecyclerViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
//        mValues = items;
//        mListener = listener;
//    }
//
//
////
//    @Override
//    public void onBindViewHolder(final ViewHolder holder, int position) {
////        holder.mItem = mValues.get(position);
////        holder.mIdView.setText(mValues.get(position).id);
//        holder.mContentView.setText(mValues.get(position).content);

//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mItem);
//                }
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return mValues.size();
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        //public final TextView mIdView;
        //public final TextView mContentView;
        //public DummyItem mItem;
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