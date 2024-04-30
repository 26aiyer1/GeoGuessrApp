package com.example.googlemapstrial;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/**
 * RecyclerAdapter class represents the RecyclerView adapter for displaying GeoList items.
 * It binds the data to the RecyclerView and handles item click events.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.GeoViewHolder> {

    /** Context of the activity. */
    private Context context;

    /** List of GeoList items. */
    private ArrayList<GeoList> geoList;

    /**
     * Constructor to initialize RecyclerAdapter with the given GeoList and context.
     *
     * @param geoList The list of GeoList items.
     * @param context The context of the activity.
     */
    public RecyclerAdapter(ArrayList<GeoList> geoList, Context context) {
        this.geoList = geoList;
        this.context = context;
    }

    /**
     * Inner class to hold the views of the RecyclerView items.
     */
    public class GeoViewHolder extends RecyclerView.ViewHolder {
        /** TextView to display the country name. */
        private TextView textViewQuestion;

        /** CardView to represent the item view. */
        private CardView cardView;

        /**
         * Constructor to initialize the views of the ViewHolder.
         *
         * @param itemView The item view.
         */
        public GeoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewQuestion = itemView.findViewById(R.id.textViewQuestion);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public GeoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design, parent, false);
        return new GeoViewHolder(view);
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull GeoViewHolder holder, @SuppressLint("RecyclerView") int position) {
        GeoList geoItem = geoList.get(position);
        holder.textViewQuestion.setText(geoItem.getCountry());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You have selected: " + geoItem.getCountry(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, GeoActivity.class);
                intent.putExtra("country", geoList.get(position).getCountry());
                intent.putExtra("city", geoList.get(position).getCity());

                context.startActivity(intent);
            }
        });
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in the adapter.
     */
    @Override
    public int getItemCount() {
        return geoList.size();
    }
}
