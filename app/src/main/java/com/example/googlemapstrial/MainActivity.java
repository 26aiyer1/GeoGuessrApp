package com.example.googlemapstrial;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {


    Button startButton;

    Button aiButton;

    Button encyclopediaButton;
    Intent maps;

    Intent trivia;

    Intent geo;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);

        aiButton = findViewById(R.id.aibutton);

        encyclopediaButton = findViewById(R.id.encyclopedia_button);

        maps = new Intent(MainActivity.this,GoogleMapsActivity.class);

        /*i = new Intent(MainActivity.this, GeminiClass.class);*/

        trivia = new Intent(MainActivity.this, TriviaGames.class);

        geo = new Intent(MainActivity.this, GeoMain.class);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(maps);
            }
        });

        /*aiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });*/

        aiButton.setOnClickListener(new View.OnClickListener() {
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


}
