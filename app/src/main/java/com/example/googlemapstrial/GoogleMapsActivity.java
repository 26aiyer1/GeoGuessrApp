package com.example.googlemapstrial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.id_map);
        mapFragment.getMapAsync(this);
    }
    // Does not work
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;

        LatLng location = new LatLng(37.313782, -121.969380);
        Marker marker = googleMap.addMarker(new MarkerOptions().position(location).title("This is Stratford Prep!"));

        map.getUiSettings().setZoomControlsEnabled(true);

        // Set a click listener for the marker
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // Handle marker click
                handleMarkerClick(marker);
                return true; // Consume the event to prevent default behavior
            }
        });

        // Set a click listener for the map
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                // Handle map click
                handleMapClick(latLng);
            }
        });
    }

    private void handleMarkerClick(Marker marker) {
        LatLng markerLatLng = marker.getPosition();
        String message = "Marker clicked! Coordinates: " + markerLatLng.latitude + ", " + markerLatLng.longitude;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void handleMapClick(LatLng latLng) {
        String message = "Map clicked! Coordinates: " + latLng.latitude + ", " + latLng.longitude;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
