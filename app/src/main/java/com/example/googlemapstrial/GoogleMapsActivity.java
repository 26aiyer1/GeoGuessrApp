package com.example.googlemapstrial;



import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import android.content.Intent;
import android.widget.Button;

public class GoogleMapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap map;

    Button back;

    Intent intent;

    String question;

    Button genQuestion;

    String countryGenerated;

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
        back = findViewById(R.id.genQuestion);
        genQuestion = findViewById(R.id.genQuestion);

        intent = new Intent(GoogleMapsActivity.this, MainActivity.class);

        map.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json ) );



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        LatLng location = new LatLng(latLng.latitude, latLng.longitude);
        Marker marker = map.addMarker(new MarkerOptions().position(location).title("marker"));
        String country = GeoCodingHelper.getCountryFromLocation(this, location.latitude, location.longitude);
        checkAnswer(country);
        marker.remove();
    }


    public void generateQuestion(){
        GenerativeModel gm = new GenerativeModel("gemini-pro",
                "AIzaSyArulVC_IsImpG9vRtDy-D8Gi4vOMEDZtc");
        GenerativeModelFutures model = GenerativeModelFutures.from(gm);

        Content content = new Content.Builder()
                .addText("Generate a random country name in the following format: (Country) ")
                .build();


        ListenableFuture<GenerateContentResponse> response = model.generateContent(content);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
                @Override
                public void onSuccess(GenerateContentResponse result) {
                    question = result.getText();
                    countryGenerated = question;

                }
                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            }, this.getMainExecutor());
        }

    }



    public void checkAnswer(String countryClicked){
        if(countryGenerated.toString().equals(countryClicked)){
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
        }
    }
}
