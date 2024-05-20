package com.example.googlemapstrial;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MapStyleOptions;

/**
 * MapWithCountries class represents the activity for displaying a Google Map and handling user interactions.
 * It allows users to interact with the map, generate questions, and check answers.
 *
 * Source: https://www.youtube.com/watch?v=_gpreGNtNCM
 */
public class MapWithCountries extends AppCompatActivity implements OnMapReadyCallback{

    /** GoogleMap object to interact with the map. */
    private GoogleMap map;

    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_with_countries);

        back = findViewById(R.id.mainback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.id_map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        } else {
            finish();
        }
    }

    /**
     * Method called when the map is ready to be used.
     * Sets up the map with styling and event listeners.
     *
     * @param googleMap The GoogleMap object.
     */
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json2));
    }
}
