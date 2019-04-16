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


        Holder.VenueViewText.setText(mData.get(Pos).getVenueId());

        if (mData.get(Pos).getIsGIF() == 1)
        {
            Glide.with(mContext).asGif().load(mData.get(Pos).getImgUri())/*.placeholder()*/.into(Holder.img_Venue);

        }
        else
        {
            Glide.with(mContext).asDrawable().load(mData.get(Pos).getImgUri()).into(Holder.img_Venue);
        }

        Holder.VenueCard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent Viwer;
                Viwer = new Intent(mContext, AptResult.class); //Sets Content to this Activity and the Specific elements of the CardviewAdaptor interacted with?
                String venueid = mData.get(Pos).getVenueId();
                Uri ImgUri = mData.get(Pos).getImgUri();

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
        ImageView img_Venue;
        CardView VenueCard;
        public ViewHolder(View App2view)
        {
            super(App2view);
            mView = App2view;
            VenueViewText = (TextView) App2view.findViewById(R.id.venue_title);

            img_Venue = /*(ImageView)*/ App2view.findViewById(R.id.venue_cover_Loader); //venue_cover_id

            VenueCard = (CardView) App2view.findViewById(R.id.VenueCardView);
        }


    }
    @Override
    public int getItemCount() {
        return mData.size();
    }


}
