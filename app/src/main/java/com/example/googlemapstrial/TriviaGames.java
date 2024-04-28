package com.example.googlemapstrial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;


public class TriviaGames extends AppCompatActivity {

    TextView triviaGameTextView;
    Button buttonA;
    Button buttonB;
    Button buttonC;
    Button buttonD;
    Button backButton;
    Button nextButton;

    GeoList firstNation;

    private ArrayList<GeoList> allNations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_games);

        String temp = loadJSONFromAsset();

        try {
            JSONArray obj = new JSONArray(temp);
            setUpGeoArrays(obj);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        triviaGameTextView = findViewById(R.id.textView);
        buttonA = findViewById(R.id.button2);
        buttonB = findViewById(R.id.button3);
        buttonC = findViewById(R.id.button4);
        buttonD = findViewById(R.id.button5);
        backButton = findViewById(R.id.button6);
        nextButton = findViewById(R.id.button7);


        Random random = new Random();

        int randomNumber = random.nextInt(245);
        int rand = random.nextInt(4)+1;

        firstNation = allNations.get(randomNumber);

        if(rand == 1){
            triviaGameTextView.setText(firstNation.getCountry());
            buttonA.setText(allNations.get(random.nextInt(245)).getCity());
            buttonB.setText(allNations.get(random.nextInt(245)).getCity());
            buttonC.setText(firstNation.getCity());
            buttonD.setText(allNations.get(random.nextInt(245)).getCity());
        }

        else if(rand == 2){
            triviaGameTextView.setText(firstNation.getCountry());
            buttonA.setText(firstNation.getCity());
            buttonB.setText(allNations.get(random.nextInt(245)).getCity());
            buttonC.setText(allNations.get(random.nextInt(245)).getCity());
            buttonD.setText(allNations.get(random.nextInt(245)).getCity());
        }
        else if(rand == 3){
            triviaGameTextView.setText(firstNation.getCountry());
            buttonA.setText(allNations.get(random.nextInt(245)).getCity());
            buttonB.setText(allNations.get(random.nextInt(245)).getCity());
            buttonC.setText(allNations.get(random.nextInt(245)).getCity());
            buttonD.setText(firstNation.getCity());
        }
        else{
            triviaGameTextView.setText(firstNation.getCountry());
            buttonA.setText(allNations.get(random.nextInt(245)).getCity());
            buttonB.setText(firstNation.getCity());
            buttonC.setText(allNations.get(random.nextInt(245)).getCity());
            buttonD.setText(allNations.get(random.nextInt(245)).getCity());
        }



        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonA.getText().toString().equals(firstNation.getCity())){
                    Toast.makeText(TriviaGames.this, "Correct!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(TriviaGames.this, "Wrong!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonB.getText().toString().equals(firstNation.getCity())){
                    Toast.makeText(TriviaGames.this, "Correct!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(TriviaGames.this, "Wrong!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonC.getText().toString().equals(firstNation.getCity())){
                    Toast.makeText(TriviaGames.this, "Correct!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(TriviaGames.this, "Wrong!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonD.getText().toString().equals(firstNation.getCity())){
                    Toast.makeText(TriviaGames.this, "Correct!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(TriviaGames.this, "Wrong!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int randomNumber = random.nextInt(245);
                int rand = random.nextInt(4)+1;

                firstNation = allNations.get(randomNumber);

                if(rand == 1){
                    triviaGameTextView.setText(firstNation.getCountry());
                    buttonA.setText(allNations.get(random.nextInt(245)).getCity());
                    buttonB.setText(allNations.get(random.nextInt(245)).getCity());
                    buttonC.setText(firstNation.getCity());
                    buttonD.setText(allNations.get(random.nextInt(245)).getCity());
                }

                else if(rand == 2){
                    triviaGameTextView.setText(firstNation.getCountry());
                    buttonA.setText(firstNation.getCity());
                    buttonB.setText(allNations.get(random.nextInt(245)).getCity());
                    buttonC.setText(allNations.get(random.nextInt(245)).getCity());
                    buttonD.setText(allNations.get(random.nextInt(245)).getCity());
                }
                else if(rand == 3){
                    triviaGameTextView.setText(firstNation.getCountry());
                    buttonA.setText(allNations.get(random.nextInt(245)).getCity());
                    buttonB.setText(allNations.get(random.nextInt(245)).getCity());
                    buttonC.setText(allNations.get(random.nextInt(245)).getCity());
                    buttonD.setText(firstNation.getCity());
                }
                else{
                    triviaGameTextView.setText(firstNation.getCountry());
                    buttonA.setText(allNations.get(random.nextInt(245)).getCity());
                    buttonB.setText(firstNation.getCity());
                    buttonC.setText(allNations.get(random.nextInt(245)).getCity());
                    buttonD.setText(allNations.get(random.nextInt(245)).getCity());
                }

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }




    public void setUpGeoArrays(JSONArray obj) {
        for (int i = 0; i < obj.length(); i++) {
            try {
                JSONObject entry = obj.getJSONObject(i);
                String country = entry.getString("country");
                String city = entry.getString("city");
                GeoList geoItem = new GeoList(country, city);
                allNations.add(geoItem);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


    private String loadJSONFromAsset () {
        String json;
        try {
            InputStream is = getAssets().open("new_geo_json.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}