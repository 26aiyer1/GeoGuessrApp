package com.example.googlemapstrial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * GeoMain class represents the main activity for the Geo Encyclopedia feature.
 * It displays a list of geographical locations and allows users to navigate back to the main activity.
 */
public class GeoMain extends AppCompatActivity {

    /** ArrayList to store the geographical locations. */
    public ArrayList<GeoList> geoList = new ArrayList<>();

    /** RecyclerView to display the list of geographical locations. */
    public RecyclerView recyclerView;

    /** Adapter for the RecyclerView. */
    public RecyclerAdapter recyclerAdapter;

    /** TextView to display the score or title. */
    TextView score;

    /** Intent to navigate back to the main activity. */
    Intent toMain;

    /** Button to navigate back to the main activity. */
    Button b;

    /**
     * Method called when the activity is created.
     * Initializes UI components, sets up RecyclerView, and loads data.
     *
     * @param savedInstanceState The saved instance state.
     */
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_main);

        Intent reciever = getIntent();

        b = findViewById(R.id.backGo);
        recyclerView = findViewById(R.id.RecyclerView);
        score = findViewById(R.id.score);

        toMain = new Intent(GeoMain.this, MainActivity.class);

        recyclerAdapter = new RecyclerAdapter(geoList, GeoMain.this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(GeoMain.this));

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(toMain);
            }
        });

        String temp = loadJSONFromAsset();

        try {
            JSONArray obj = new JSONArray(temp);
            setUpGeoArrays(obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        score.setText("Encyclopedia");
    }

    /**
     * Parses the JSON data and sets up the GeoList array.
     *
     * @param obj The JSONArray containing the JSON data.
     */
    public void setUpGeoArrays(JSONArray obj) {
        for (int i = 0; i < obj.length(); i++) {
            try {
                JSONObject entry = obj.getJSONObject(i);
                String country = entry.getString("country");
                String city = entry.getString("city");
                GeoList geoItem = new GeoList(country, city);
                geoList.add(geoItem);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Loads JSON data from the asset folder.
     *
     * @return The JSON data as a string.
     */
    public String loadJSONFromAsset() {
        String json = "";
        try {
            InputStream is = getAssets().open("new_geo_json.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
