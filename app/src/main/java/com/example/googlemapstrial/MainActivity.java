package com.example.googlemapstrial;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.id_map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

    }
    //Commit practice

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        map = googleMap;

        map.setOnMapClickListener(this);
        map.getUiSettings().setZoomControlsEnabled(true);

        map.setMapType( GoogleMap.MAP_TYPE_NORMAL );

        try
        {
            boolean success = map.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json ) );

            if( !success )
            {
                Log.e( TAG, "Style parsing failed." );
            }
        }
        catch( Resources.NotFoundException e )
        {
            Log.e( TAG, "Can't find style. Error: ", e );
        }



       // map.setMapType(MapStyleOptions.loadRawResourceStyle(this, R.ra));

    }


    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        LatLng location = new LatLng(latLng.latitude, latLng.longitude);
        Marker marker = map.addMarker(new MarkerOptions().position(location).title("marker"));
        String country = GeoCodingHelper.getCountryFromLocation(this, location.latitude, location.longitude);
        Toast.makeText(this, country, Toast.LENGTH_SHORT).show();
        //remove marker after toast message
        marker.remove();

    }
}