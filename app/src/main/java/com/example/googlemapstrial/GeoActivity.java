package com.example.googlemapstrial;

import androidx.appcompat.app.AppCompatActivity;
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
 * GeoActivity class represents the activity displaying information about a specific country and its capital.
 */
public class GeoActivity extends AppCompatActivity {

    private TextView countryName;
    private TextView capitalName;
    private Button back;
    private ArrayList<GeoList> geoList = new ArrayList<>();

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo);

        // Initialize UI elements
        countryName = findViewById(R.id.countryName);
        capitalName = findViewById(R.id.capitalName);
        back = findViewById(R.id.backed);

        // Load JSON data from asset file
        String temp = loadJSONFromAsset();

        // Parse JSON data and set up GeoList array
        try {
            JSONArray obj = new JSONArray(temp);
            setUpGeoArrays(obj);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        // Retrieve country and city information from intent
        String country = getIntent().getStringExtra("country");
        String city = getIntent().getStringExtra("city");

        // Initialize intent to return to GeoMain activity
        Intent intent = new Intent(GeoActivity.this, GeoMain.class);

        // Display country and capital information
        countryName.setText("Country: " + country);
        capitalName.setText("Capital: " + city);

        // Set onClick listener for back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

    /**
     * Parse the JSON array and populate the GeoList array with country-city pairs.
     *
     * @param obj The JSON array containing country-city pairs.
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
     * Load JSON data from the asset file.
     *
     * @return The JSON data as a string.
     */
    private String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("new_geo_json.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return json;
    }
}
