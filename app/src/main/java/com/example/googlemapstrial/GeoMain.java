package com.example.googlemapstrial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
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

    Button b;


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
        String json = "";
        try {
            InputStream is = getAssets().open("new_geo_json.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            System.out.println(buffer);
            is.read(buffer);
            is.close();
            System.out.println("file read, parsing data");
            json = new String(buffer, StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("file parsed");
        return json;
    }
}