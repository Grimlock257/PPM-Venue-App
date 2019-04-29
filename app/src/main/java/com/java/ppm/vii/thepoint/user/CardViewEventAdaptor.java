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
import android.widget.Filter;
import android.widget.Filterable;
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

import java.util.ArrayList;
import java.util.List;

public /*abstract*/ class CardViewEventAdaptor extends RecyclerView.Adapter<CardViewEventAdaptor.ViewHolder> implements Filterable
{
    private Context mContext;
    private List<VenueCardViewArray> mData;
    private ImageView VImages;
    private List<VenueCardViewArray> Filterable;
    //private String URL;


    public CardViewEventAdaptor(Context adapterContext, List<VenueCardViewArray> mData /*List<VenueCardViewArray> Filterable*/) //Fixed bug where List parameter was accidentally set to the old name of mData, mValues, which causes a NPE where getItemCOunt returned as null as the Viewadpator could npt see mData due to this Typo  //Do Not need dummy as we need the VenueImplementable class Structure actually Containing the Venue Data grabed from the Web Side. //DO We Need the Dummy to collect the List array for the GUI?
    {
        this.mContext = adapterContext;  //Adds Context to the List?
        this.mData = mData;
        this.Filterable = mData;
        //this
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View App2view;
        LayoutInflater mInflator = LayoutInflater.from(mContext);
        App2view = mInflator.inflate(R.layout.cardview_user_show_event, parent, false);
        //URL = "http://goo.gl/gEgYUd";
        return new ViewHolder(App2view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder Holder, final int Pos) {


        Holder.VenueViewText.setText(Filterable.get(Pos).getVenueId());

        /*Uri load = Uri.parse("https://cdn.dribbble.com/users/108390/screenshots/2882839/spinner-loop.gif"); //tried to add a loading animation whilst the images are being loaded into the Adaptor
        String Load = "https://cdn.dribbble.com/users/108390/screenshots/2882839/spinner-loop.gif";*/
        if (mData.get(Pos).getIsGIF() == 1) {
            Glide.with(mContext).asGif()/*.placeholder(Uri.parse("https://cdn.dribbble.com/users/108390/screenshots/2882839/spinner-loop.gif"))*/.load(mData.get(Pos).getImgUri())/*.placeholder()*/.into(Holder.img_Venue);

        } else {
            Glide.with(mContext).asDrawable().load(mData.get(Pos).getImgUri()).into(Holder.img_Venue);
        }

        Holder.VenueCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Viwer;
                Viwer = new Intent(mContext, AptResult.class); //Sets Content to this Activity and the Specific elements of the CardviewAdaptor interacted with?
                String venueid = Filterable.get(Pos).getVenueId();
                Uri ImgUri = Filterable.get(Pos).getImgUri();

                Viwer.putExtra("venueid", venueid);/*mData.get(Pos).getVenueId())*/
                ;
                //Viwer.putExtra("imgID", );
                Viwer.putExtra("ImgLoc", ImgUri/*Holder.UriGrabber*/); //IS this Broken?
                //Viwer.toUri(ImgUri); //IS this Broken?
                v.getContext().startActivity(Viwer);


            }

        });
    }

    @Override
    public int getItemCount()
    {
        return Filterable.size();
    }

    @Override
    public Filter getFilter/*getFiltered*/()
    {
        return new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence constraint)
            {

                String Key = constraint.toString();
                if (Key.isEmpty())
                {
                    Filterable = mData;
                }
                else
                    {
                    java.util.List<VenueCardViewArray> lstLev = new ArrayList<>();
                    for (VenueCardViewArray Coord : mData)
                    {
                        if (Coord.getVenueId().toLowerCase().contains(Key.toLowerCase()))
                        {
                            lstLev.add(Coord);

                        }

                        Filterable = lstLev;

                    }
                }
                FilterResults filterResults = new FilterResults();

                filterResults.values = Filterable;

                return filterResults;


            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results)
            {
                Filterable = (List<VenueCardViewArray>) results.values;
                notifyDataSetChanged();

            }


        };
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



}
