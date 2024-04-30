package com.example.googlemapstrial;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

/**
 * MainActivity class represents the main activity of the application.
 */
public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button triviaButton;
    private Button encyclopediaButton;
    private Button creditsScreen;
    private Intent maps;
    private Intent trivia;
    private Intent geo;
    private Intent credits;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons
        startButton = findViewById(R.id.startButton);
        triviaButton = findViewById(R.id.triviaButton);
        encyclopediaButton = findViewById(R.id.encyclopedia_button);
        creditsScreen = findViewById(R.id.creditsScreenButton);

        // Initialize intents
        maps = new Intent(MainActivity.this, GoogleMapsActivity.class);
        trivia = new Intent(this, TriviaGames.class);
        geo = new Intent(this, GeoMain.class);
        credits = new Intent(this, CreditsScreen.class);

        // Set onClick listeners for buttons
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(maps);
            }
        });

        triviaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(trivia);
            }
        });

        encyclopediaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(geo);
            }
        });

        creditsScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(credits);
            }
        });
    }
}
