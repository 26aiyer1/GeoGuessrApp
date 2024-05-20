package com.example.googlemapstrial;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GeoMain extends AppCompatActivity {

    private ArrayList<GeoList> geoList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private SearchView searchView;
    private List<GeoList> filteredList = new ArrayList<>();

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_main);

        Button backButton = findViewById(R.id.backGo);
        recyclerView = findViewById(R.id.RecyclerView);
        searchView = findViewById(R.id.search);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GeoMain.this, MainActivity.class));
            }
        });

        recyclerAdapter = new RecyclerAdapter(geoList, GeoMain.this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(GeoMain.this));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText.toLowerCase(Locale.getDefault()));
                return true;
            }
        });

        String jsonData = loadJSONFromAsset();
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            setUpGeoArrays(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setUpGeoArrays(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject entry = jsonArray.getJSONObject(i);
                String country = entry.getString("country");
                String city = entry.getString("city");
                GeoList geoItem = new GeoList(country, city);
                geoList.add(geoItem);
            } catch (JSONException e) {
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
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    private void filter(String text) {
        filteredList.clear();
        for (GeoList item : geoList) {
            if (item.getCountry().toLowerCase(Locale.getDefault()).contains(text)) {
                filteredList.add(item);
            }
        }
        recyclerAdapter.filterList((ArrayList<GeoList>) filteredList);
    }
}
