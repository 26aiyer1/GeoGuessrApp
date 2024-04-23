package com.example.googlemapstrial;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.GeoViewHolder> {

    private Context context;
    private ArrayList<GeoList> geoList;


    public RecyclerAdapter(ArrayList<GeoList> geoList, Context context) {
        this.geoList = geoList;
        this.context = context;
    }

    public class GeoViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewQuestion;
        private CardView cardView;

        public GeoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewQuestion = itemView.findViewById(R.id.textViewQuestion);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    @NonNull
    @Override
    public GeoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design, parent, false);
        return new GeoViewHolder(view);
    }

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

    @Override
    public int getItemCount() {
        return geoList.size();
    }
}


