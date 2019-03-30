package com.java.ppm.vii.thepoint.admin;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.java.ppm.vii.thepoint.R;
import com.java.ppm.vii.thepoint.database.entity.Event;

import java.util.ArrayList;

/**
 * Stores the information to go inside the RecyclerView
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private ArrayList<Event> eventList;
    private OnItemClickListener listener;

    /**
     * Creates a new EventAdapter to be used to feed the event information to the RecyclerView
     *
     * @param eventList The list of events to feed to the RecyclerView
     */
    EventAdapter(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }

    /**
     * Contains methods that must implemented for the EventAdapter class that is for the
     * RecyclerView that contains all the events in the admin area
     */
    public interface OnItemClickListener {
        /**
         * Called when the edit button was clicked
         *
         * @param position The position of the card in the RecyclerView that was clicked
         */
        void onEditClick(int position);

        /**
         * Called when the delete button was clicked
         *
         * @param position The position of the card in the RecyclerView that was clicked
         */
        void onDeleteClick(int position);
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
    static class EventViewHolder extends RecyclerView.ViewHolder {
        // Views within each card
        TextView eventTitle;
        TextView eventDate;
        ImageView deleteImage;
        ImageView editImage;

        /**
         * Create a new EventViewHolder for each card within the RecyclerView
         *
         * @param itemView The view for the card
         * @param listener The listener interaction on the card
         */
        EventViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            // Create references to views within the card
            eventTitle = itemView.findViewById(R.id.eventTitle);
            eventDate = itemView.findViewById(R.id.eventDate);

            editImage = itemView.findViewById(R.id.image_edit);
            deleteImage = itemView.findViewById(R.id.image_delete);

            // Create onClickListener for the editImage (that is being used as a button)
            editImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();

                        // Make sure the position returned is a valid position
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onEditClick(position);
                        }
                    }
                }
            });

            // Create onClickListener for the deleteImage (that is being used as a button)
            deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();

                        // Make sure the position returned is a valid position
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
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
     * @return The newly created EventViewHolder
     */
    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_admin_overview_list, viewGroup, false);

        return new EventViewHolder(view, listener);
    }

    /**
     * Binds the information from the specified index of the eventList to the views within the
     * new ViewHolder
     *
     * @param eventViewHolder The ViewHolder that is having information bound to it
     * @param i               The index within the eventList that we are binding
     */
    @Override
    public void onBindViewHolder(@NonNull EventViewHolder eventViewHolder, int i) {
        Event currentEvent = eventList.get(i);

        eventViewHolder.eventTitle.setText(currentEvent.getTitle());
        eventViewHolder.eventDate.setText(currentEvent.getDate());
    }

    /**
     * Get the size of the eventList
     *
     * @return Get the size of the eventList
     */
    @Override
    public int getItemCount() {
        return eventList.size();
    }
}