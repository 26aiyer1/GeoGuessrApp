package com.example.googlemapstrial;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * MainActivity class represents the main activity of the application.
 */
public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button triviaButton;
    private Button encyclopediaButton;

    private SharedPreferences sharedPreferences;
    private Intent maps;
    private Intent trivia;
    private Intent geo;

    private Intent toCredits;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Initialize buttons
        startButton = findViewById(R.id.startButton);
        triviaButton = findViewById(R.id.triviaButton);
        encyclopediaButton = findViewById(R.id.encyclopedia_button);

        // Initialize intents
        maps = new Intent(MainActivity.this, GoogleMapsActivity.class);
        trivia = new Intent(MainActivity.this, TriviaGames.class);
        geo = new Intent(MainActivity.this, GeoMain.class);
        toCredits = new Intent(MainActivity.this, Credits.class);

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

    }

    // Save data to SharedPreferences
    private void saveData(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    // Retrieve data from SharedPreferences
    private boolean getData(String key) {
        return sharedPreferences.getBoolean(key, false);
    }
}
