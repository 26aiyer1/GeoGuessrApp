package com.example.googlemapstrial;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.content.Intent;
import android.widget.Button;

public class GoogleMapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap map;

    Button back;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps_activity);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.id_map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        map = googleMap;
        map.setOnMapClickListener(this);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.setMapType( GoogleMap.MAP_TYPE_NORMAL );
        back = findViewById(R.id.back);

        intent = new Intent(GoogleMapsActivity.this, MainActivity.class);

        map.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json ) );



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        // Handle map click
        LatLng location = new LatLng(latLng.latitude, latLng.longitude);
        Marker marker = map.addMarker(new MarkerOptions().position(location).title("marker"));
        String country = GeoCodingHelper.getCountryFromLocation(this, location.latitude, location.longitude);
        Toast.makeText(this, country, Toast.LENGTH_SHORT).show();
        // Remove marker after toast message
        marker.remove();
    }
}
