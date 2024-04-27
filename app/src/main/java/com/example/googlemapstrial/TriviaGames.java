package com.example.googlemapstrial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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


public class TriviaGames extends AppCompatActivity {

    TextView triviaGameTextView;
    Button buttonA;
    Button buttonB;
    Button buttonC;
    Button buttonD;
    Button backButton;
    Button nextButton;

    Intent back;
    private ArrayList<Nation> allNations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_games);

        Intent receiver = getIntent();

        generateNations();

        triviaGameTextView = findViewById(R.id.Question);
        buttonA = findViewById(R.id.button2);
        buttonB = findViewById(R.id.button3);
        buttonC = findViewById(R.id.button4);
        buttonD = findViewById(R.id.button5);
        backButton = findViewById(R.id.button6);
        nextButton = findViewById(R.id.button7);
        back = new Intent(this, MainActivity.class);

        // Create a Random object
        Random random = new Random();

        // Generate a random number between 0 and 244
        int randomNumber = random.nextInt(245); // 0 to 244 inclusive
        Nation firstNation = allNations.get(randomNumber);
        triviaGameTextView.setText(firstNation.getCountry());
        buttonA.setText(allNations.get(random.nextInt(245)).getCity());
        buttonB.setText(allNations.get(random.nextInt(245)).getCity());
        buttonC.setText(firstNation.getCity());
        buttonD.setText(allNations.get(random.nextInt(245)).getCity());


        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TriviaGames.this, "Incorrect", Toast.LENGTH_SHORT).show();
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TriviaGames.this, "Incorrect", Toast.LENGTH_SHORT).show();
            }
        });


        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TriviaGames.this, "Correct", Toast.LENGTH_SHORT).show();
            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TriviaGames.this, "Incorrect", Toast.LENGTH_SHORT).show();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();

                // Generate a random number between 0 and 244
                int randomNumber = random.nextInt(245); // 0 to 244 inclusive
                Nation firstNation = allNations.get(randomNumber);
                triviaGameTextView.setText(firstNation.getCountry());
                buttonA.setText(allNations.get(random.nextInt(245)).getCity());
                buttonB.setText(allNations.get(random.nextInt(245)).getCity());
                buttonC.setText(firstNation.getCity());
                buttonD.setText(allNations.get(random.nextInt(245)).getCity());
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(back);
            }
        });
    }

    //this method reads data from a JSON file in the assets folder, parses the data, and creates Movie objects for each entry
    private void generateNations () {
        try {
            //load JSON data from the assets folder
            String json = loadJSONFromAsset();
            //create a JSONObject from the JSON string
            JSONObject jsonObject = new JSONObject(json);
            //get the JSONArray containing nation objects
            JSONArray nationObjects = jsonObject.getJSONArray("nations");

            //iterate through each nation object in the JSONArray
            for (int i = 0; i < nationObjects.length(); i++) {
                // Get a single nation object from the array
                JSONObject nationObject = nationObjects.getJSONObject(i);
                //extract country name from the JSON object
                String country = nationObject.getString("country");
                //extract capital city
                String city = nationObject.getString("city");
                //create a nation object using the extracted information
                Nation nation = new Nation(country, city);
                allNations.add(nation);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //this method reads and loads JSON data from a file in the assets folder
    public String loadJSONFromAsset() {
        String json = "";
        try {
            InputStream is = getAssets().open("new_geo_json.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            System.out.println(buffer);
            is.read(buffer);
            is.close();
            System.out.println("file read, parsing data");
            json = new String(buffer, StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("file parsed");
        return json;
    }
}