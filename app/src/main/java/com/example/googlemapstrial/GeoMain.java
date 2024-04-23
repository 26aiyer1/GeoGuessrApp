package com.example.googlemapstrial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;

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
import android.content.Intent;

public class GeoMain extends AppCompatActivity {

    public ArrayList<GeoList> geoList = new ArrayList<>();

    public RecyclerView recyclerView;
    public RecyclerAdapter recyclerAdapter;

    TextView score;


    Intent toMain;


    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_main);
        recyclerView = findViewById(R.id.RecyclerView);
        score = findViewById(R.id.score);

        toMain = new Intent(GeoMain.this, MainActivity.class);

        recyclerAdapter = new RecyclerAdapter(geoList, GeoMain.this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(GeoMain.this));



        String temp = loadJSONFromAsset();

        try {
            JSONArray obj = new JSONArray(temp);
            setUpGeoArrays(obj);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        score.setText("Encyclopedia");


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

    public String loadJSONFromAsset() {
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