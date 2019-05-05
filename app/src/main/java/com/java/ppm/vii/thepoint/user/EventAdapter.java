package com.java.ppm.vii.thepoint.user;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.java.ppm.vii.thepoint.R;
import com.java.ppm.vii.thepoint.database.API;
import com.java.ppm.vii.thepoint.database.entity.Event;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> implements Filterable {

    private List<Event> eventList;
    private List<Event> filteredEventList;

    private OnItemClickListener listener;

    /**
     * Creates a new EventAdapter to be used to feed the event information to the RecyclerView
     *
     * @param eventList The list of events to feed to the RecyclerView
     */
    EventAdapter(List<Event> eventList) {
        this.eventList = eventList;
        this.filteredEventList = eventList;
    }

    /**
     * Contains methods that must implemented for the EventAdapter class that is for the
     * RecyclerView that contains all the events in the admin area
     */
    public interface OnItemClickListener {
        /**
         * Called when the event card was clicked
         *
         * @param event The Event that the card in the RecyclerView represented
         */
        void onEventCardClick(Event event);
    }

    /**
     * Set the OnItemClickListener
     *
     * @param listener The new listener
     */
    void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            public FilterResults performFiltering(CharSequence constraint) {
                String searchKey = constraint.toString();
                if (searchKey.isEmpty()) {
                    filteredEventList = eventList;
                } else {
                    List<Event> lstLev = new ArrayList<>();

                    for (Event event : eventList) {
                        if (event.getTitle().toLowerCase().contains(searchKey.toLowerCase())) {
                            lstLev.add(event);
                        }
                        filteredEventList = lstLev;
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredEventList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                notifyDataSetChanged();
            }
        };
    }

    /**
     * Stores information about each card within the RecyclerView
     */
    class EventViewHolder extends RecyclerView.ViewHolder {
        // Views within each card
        CardView eventCard;
        ImageView eventImage;
        TextView VenueViewText;

        /**
         * Create a new EventViewHolder for each card within the RecyclerView
         *
         * @param itemView The view for the card
         */
        EventViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            // Create references to views within the card
            eventCard = itemView.findViewById(R.id.event_card_view);
            eventImage = itemView.findViewById(R.id.event_image);
            VenueViewText = itemView.findViewById(R.id.event_title);

            // Create onClickListener for the event card
            eventCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        // Retrieve the clicked card
                        int position = getAdapterPosition();

                        // Make sure the position returned is a valid position
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onEventCardClick(filteredEventList.get(getAdapterPosition()));
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_user_show_event, viewGroup, false);

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
    public void onBindViewHolder(@NonNull final EventViewHolder eventViewHolder, int i) {
        Event currentEvent = filteredEventList.get(i);

        eventViewHolder.VenueViewText.setText(currentEvent.getTitle());
        Glide.with(eventViewHolder.itemView.getContext()).load(API.getEventImageURL() + currentEvent.getMainImage()).into(eventViewHolder.eventImage); //Must load pos into FIlterable ArryList to Allow Glide to implement teh images correctly!
    }

    /**
     * Get the size of the eventList
     *
     * @return Get the size of the eventList
     */
    @Override
    public int getItemCount() {
        return filteredEventList.size();
    }
}
