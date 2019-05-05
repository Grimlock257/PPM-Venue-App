package com.java.ppm.vii.thepoint.user;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    /**
     * Creates a new AboutAdapter to be used to feed the about information to the RecyclerView
     *
     * @param aboutList The list of events to feed to the RecyclerView
     */
    AboutAdapter(ArrayList<About> aboutList) {
        this.aboutList = aboutList;
    }

    /**
     * Stores information about each card within the RecyclerView
     */
    static class AboutViewHolder extends RecyclerView.ViewHolder {
        // Views within each card
        TextView aboutTitle;
        TextView aboutInfo;

        ImageView aboutImage;

        /**
         * Create a new AboutViewHolder for each card within the RecyclerView
         *
         * @param itemView The view for the card
         */
        AboutViewHolder(@NonNull View itemView) {
            super(itemView);

            // Create references to views within the card
            aboutTitle = itemView.findViewById(R.id.aboutTitle);
            aboutInfo = itemView.findViewById(R.id.aboutInfo);

            aboutImage = itemView.findViewById(R.id.aboutImage);
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

        return new AboutViewHolder(view);
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

        if (currentAbout.getImg() != null) {
            Glide.with(aboutViewHolder.itemView.getContext()).load(API.getAboutUsImageURL() + currentAbout.getImg()).into(aboutViewHolder.aboutImage); //Must load pos into FIlterable ArryList to Allow Glide to implement teh images correctly!
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