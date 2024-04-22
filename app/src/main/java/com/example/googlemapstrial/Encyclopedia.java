package com.example.googlemapstrial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Encyclopedia extends AppCompatActivity {

    public ArrayList<GeoList> geoList = new ArrayList<>();

    public RecyclerView recyclerView;
    public RecyclerAdapter recyclerAdapter;

    Button back;

    Intent intent;




    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encyclopedia);
        recyclerView = findViewById(R.id.RecyclerView);

        recyclerAdapter = new RecyclerAdapter(geoList, Encyclopedia.this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Encyclopedia.this));

        back = findViewById(R.id.backk);
        intent = new Intent(Encyclopedia.this, MainActivity.class);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });


        String temp = loadJSONFromAsset();

        try {
            JSONArray obj = new JSONArray(temp);
            setUpGeoArrays(obj);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

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