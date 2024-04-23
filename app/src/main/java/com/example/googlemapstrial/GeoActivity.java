package com.example.googlemapstrial;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;


public class GeoActivity extends AppCompatActivity {

    TextView countryName;

    TextView capitalName;

    Button back;

    ArrayList<GeoList> geoList = new ArrayList<>();

    String city_holder = "";

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo);
        countryName = findViewById(R.id.countryName);
        capitalName = findViewById(R.id.capitalName);
        back = findViewById(R.id.backed);

        String temp = loadJSONFromAsset();

        try {
            JSONArray obj = new JSONArray(temp);
            setUpGeoArrays(obj);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        String country = getIntent().getStringExtra("country");
        String city = getIntent().getStringExtra("city");

        Intent intent = new Intent(GeoActivity.this, GeoMain.class);

        countryName.setText("Country: "+country);
        capitalName.setText("Capital: "+city);




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });


    }

    public void setUpGeoArrays(JSONArray obj) {
        for (int i = 0; i < obj.length(); i++) {
            try {
                JSONObject entry = obj.getJSONObject(i);
                String country = entry.getString("country");
                String city = entry.getString("city");
                GeoList geoItem = new GeoList(country, city);
                geoList.add(geoItem);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

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