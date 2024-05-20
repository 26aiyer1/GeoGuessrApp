package com.example.googlemapstrial;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

/**
 * GoogleMapsActivity class represents the activity for displaying a Google Map and handling user interactions.
 * It allows users to interact with the map, generate questions, and check answers.
 *
 * Source: https://www.youtube.com/watch?v=_gpreGNtNCM
 */
public class GoogleMapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    /** GoogleMap object to interact with the map. */
    private GoogleMap map;

    /** TextView to display the country name. */
    TextView country;

    /** Button to navigate back to the main activity. */
    Button back;

    /** Intent to navigate back to the main activity. */
    Intent intent;

    /** Button to generate a question. */
    Button genQuestion;

    /** Generated question. */
    String question;

    /** Generated country name. */
    String countryGenerated;

    /**
     * Method called when the activity is created.
     * Initializes UI components, sets up the map, and handles button clicks.
     *
     * @param savedInstanceState The saved instance state.
     */
    @SuppressLint({"CutPasteId", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps_activity);
        country = findViewById(R.id.country);
        back = findViewById(R.id.asdf);
        genQuestion = findViewById(R.id.genQuestion);

        intent = new Intent(GoogleMapsActivity.this, MainActivity.class);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        genQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateQuestion();
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.id_map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    /**
     * Method called when the map is ready to be used.
     * Sets up the map with styling and event listeners.
     *
     * @param googleMap The GoogleMap object.
     */
    @SuppressLint("CutPasteId")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        map.setOnMapClickListener(this);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.setMapType( GoogleMap.MAP_TYPE_NORMAL );
        map.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json ) );
    }

    /**
     * Method called when the map is clicked.
     * Adds a marker to the clicked location and checks the answer.
     *
     * @param latLng The clicked LatLng coordinates.
     */
    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        LatLng location = new LatLng(latLng.latitude, latLng.longitude);
        Marker marker = map.addMarker(new MarkerOptions().position(location).title("marker"));
        String country = GeoCodingHelper.getCountryFromLocation(this, location.latitude, location.longitude);
        checkAnswer(country);
        marker.remove();
    }

    /**
     * Generates a question using Generative AI.
     * Displays the generated question in the UI.
     *
     * Source: https://ai.google.dev/gemini-api/docs/get-started/android#java
     */
    public void generateQuestion(){
        Toast.makeText(this, "Working on it!", Toast.LENGTH_SHORT).show();
        GenerativeModel gm = new GenerativeModel("gemini-pro",
                "AIzaSyArulVC_IsImpG9vRtDy-D8Gi4vOMEDZtc");
        GenerativeModelFutures model = GenerativeModelFutures.from(gm);

        Content content = new Content.Builder()
                .addText("Name a random country in the world with each one having equal chances of being selected. Just say the name of the country.")
                .build();


        ListenableFuture<GenerateContentResponse> response = model.generateContent(content);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
                @Override
                public void onSuccess(GenerateContentResponse result) {
                    question = result.getText();
                    countryGenerated = question;
                    country.setText(question);

                }
                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            }, this.getMainExecutor());
        }

    }

    /**
     * Checks the user's answer against the generated country name.
     * Displays a toast message indicating whether the answer is correct or wrong.
     *
     * Source: https://ai.google.dev/gemini-api/docs/get-started/android#java
     *
     * @param countryClicked The country name clicked by the user.
     */
    public void checkAnswer(String countryClicked){
        if(countryGenerated.toString().equals(countryClicked)){
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
        }
    }
}
