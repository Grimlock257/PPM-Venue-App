package com.java.ppm.vii.thepoint.user;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.java.ppm.vii.thepoint.R;
import com.java.ppm.vii.thepoint.database.API;
import com.java.ppm.vii.thepoint.database.entity.About;

import java.util.ArrayList;

/**
 * Stores the information to go inside the RecyclerView
 */
public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.AboutViewHolder> {

    private ArrayList<About> aboutList;

    private OnItemClickListener listener;

    /**
     * Creates a new AboutAdapter to be used to feed the about information to the RecyclerView
     *
     * @param aboutList The list of events to feed to the RecyclerView
     */
    AboutAdapter(ArrayList<About> aboutList) {
        this.aboutList = aboutList;
    }

    /**
     * Contains methods that must implemented for the AboutAdapter class that is for the
     * RecyclerView that contains all the About Us sections
     */
    public interface OnItemClickListener {
        /**
         * Called when the Get Directions button was clicked
         */
        void onDirectionsButtonClicked();
    }

    /**
     * Set the OnItemClickListener
     *
     * @param listener The new listener
     */
    void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * Stores information about each card within the RecyclerView
     */
    static class AboutViewHolder extends RecyclerView.ViewHolder {
        // Views within each card
        TextView aboutTitle;
        TextView aboutInfo;

        ImageView aboutImage;
        WebView aboutMap; // Used for the Directions section
        Button aboutGetDirectionsBtn; // Used for the Directions section

        /**
         * Create a new AboutViewHolder for each card within the RecyclerView
         *
         * @param itemView The view for the card
         */
        AboutViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            // Create references to views within the card
            aboutTitle = itemView.findViewById(R.id.aboutTitle);
            aboutInfo = itemView.findViewById(R.id.aboutInfo);

            aboutImage = itemView.findViewById(R.id.aboutImage);
            aboutMap = itemView.findViewById(R.id.aboutMap);
            aboutGetDirectionsBtn = itemView.findViewById(R.id.buttonAboutMapDirection);

            // Create onClickListener for the event card
            aboutGetDirectionsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onDirectionsButtonClicked();
                    }
                }
            });
        }
    }

    /**
     * Creates a new ViewHolder, the card, within the RecyclerView
     *
     * @param viewGroup The parent ViewGroup under which the new ViewHolder will be created
     * @param i         The view type of the new ViewGroup
     *
     * @return The newly created AboutViewHolder
     */
    @NonNull
    @Override
    public AboutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_user_about, viewGroup, false);

        return new AboutViewHolder(view, listener);
    }

    /**
     * Binds the information from the specified index of the aboutList to the views within the
     * new ViewHolder
     *
     * @param aboutViewHolder The ViewHolder that is having information bound to it
     * @param i               The index within the aboutList that we are binding
     */
    @Override
    public void onBindViewHolder(@NonNull AboutViewHolder aboutViewHolder, int i) {
        About currentAbout = aboutList.get(i);

        aboutViewHolder.aboutTitle.setText(currentAbout.getTitle());
        aboutViewHolder.aboutInfo.setText(currentAbout.getInfo());

        // Load the About Us section image (if available) into the ImageView OR set up the WebView IF the img property is set to 'map'
        if (!currentAbout.getImg().equals("")) {
            if (currentAbout.getImg().equals("map")) {
                // Pass the iFrame to the WebView, enable JavaScript (as Google Maps iFrame requires it) and set the WebView to be visible
                String mapIFrame = "<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d601.502710427027!2d-1.184326070747606!3d52.91223230747988!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x0!2zNTLCsDU0JzQ0LjAiTiAxwrAxMScwMS42Ilc!5e0!3m2!1sen!2suk!4v1556984345173!5m2!1sen!2suk\" allowfullscreen></iframe>";
                aboutViewHolder.aboutMap.getSettings().setJavaScriptEnabled(true);
                aboutViewHolder.aboutMap.loadData(mapIFrame, "text/html", null);
                aboutViewHolder.aboutGetDirectionsBtn.setVisibility(View.VISIBLE);
            } else {
                Glide.with(aboutViewHolder.itemView.getContext()).load(API.getAboutUsImageURL() + currentAbout.getImg()).into(aboutViewHolder.aboutImage);
            }
        }
    }

    /**
     * Get the size of the aboutList
     *
     * @return Get the size of the aboutList
     */
    @Override
    public int getItemCount() {
        return aboutList.size();
    }
}