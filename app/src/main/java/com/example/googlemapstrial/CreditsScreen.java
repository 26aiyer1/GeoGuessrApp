package com.example.googlemapstrial;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreditsScreen extends AppCompatActivity {

    Button backFromCredits;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits_screen);

        backFromCredits = findViewById(R.id.backFromCreditsButton);

        Intent headBack = new Intent(CreditsScreen.this, MainActivity.class);

        backFromCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(headBack);
            }
        });
    }
}