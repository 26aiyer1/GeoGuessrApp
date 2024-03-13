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

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener {

    private GoogleMap map;

    private Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.id_map);
        mapFragment.getMapAsync(this);

        /*double latitude = 37.313782;
        double longitude = -121.969380;
        String country = GeoCodingHelper.getCountryFromLocation(this, latitude, longitude);
        Toast.makeText(this, country, Toast.LENGTH_SHORT).show();*/
    }
    //Commit practice

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        LatLng location = new LatLng(37.313782, -121.969380);
        marker = map.addMarker(new MarkerOptions()
                .position(location)
                .draggable(true)
                .title("marker")
        );

        map.setOnMarkerDragListener(this);
        //googleMap.addMarker(new MarkerOptions().position(location).title("This is Stratford Prep!"));
        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,1));
        map.getUiSettings().setZoomControlsEnabled(true);


    }


    @Override
    public void onMarkerDrag(@NonNull Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(@NonNull Marker marker) {
        String country = GeoCodingHelper.getCountryFromLocation(this, marker.getPosition().latitude, marker.getPosition().longitude);
        Toast.makeText(this, country, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMarkerDragStart(@NonNull Marker marker) {

    }
}