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
    Intent intent;

    Intent i;

    Intent i2;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);

        aiButton = findViewById(R.id.aibutton);

        encyclopediaButton = findViewById(R.id.encyclopedia_button);

        intent = new Intent(MainActivity.this,GoogleMapsActivity.class);

        i = new Intent(MainActivity.this, GeminiClass.class);

        i2 = new Intent(MainActivity.this, GeoMain.class);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        aiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });

        encyclopediaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i2);
            }
        });


    }


}
