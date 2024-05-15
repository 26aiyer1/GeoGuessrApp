package com.example.googlemapstrial;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

/**
 * TriviaGames class represents the activity for the trivia game.
 * It displays a question with multiple-choice answers and checks if the selected answer is correct.
 */
public class TriviaGames extends AppCompatActivity {

    /** TextView for displaying the trivia question. */
    TextView triviaGameTextView;

    /** Buttons for multiple-choice answers. */
    Button buttonA, buttonB, buttonC, buttonD;

    /** Buttons for navigating to the next question or returning to the previous screen. */
    Button backButton, nextButton;

    /** TextView for displaying the score. **/
    TextView scoreTracker;

    /** SharedPreferences for storing the score. **/
    SharedPreferences sharedPreferences;

    /** Score key for SharedPreferences. **/
    private static final String SCORE_KEY = "score";

    /** Integer counter to check how many answers the user gets wrong consecutively. **/
    private int consecutiveWrongAnswers = 0;

    /** The first nation for the current trivia question. */
    GeoList firstNation;

    /** List of all nations loaded from JSON. */
    private ArrayList<GeoList> allNations = new ArrayList<>();

    /**
     * Called when the activity is starting. This is where most initialization should go.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_games);
        sharedPreferences = getSharedPreferences("TriviaScore", Context.MODE_PRIVATE);

        // Load nations from JSON file
        String temp = loadJSONFromAsset();
        try {
            JSONArray obj = new JSONArray(temp);
            setUpGeoArrays(obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Initialize views and buttons
        triviaGameTextView = findViewById(R.id.textView);
        scoreTracker = findViewById(R.id.scoreTracker);
        buttonA = findViewById(R.id.button2);
        buttonB = findViewById(R.id.button3);
        buttonC = findViewById(R.id.button4);
        buttonD = findViewById(R.id.button5);
        backButton = findViewById(R.id.button6);
        nextButton = findViewById(R.id.button7);

        // Generate a random question
        generateQuestion();

        // Set click listeners for answer buttons
        setAnswerButtonListeners();

        // Set click listener for the next button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generate a new random question
                generateQuestion();
            }
        });

        // Set click listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the activity and return to the previous screen
                finish();
            }
        });
    }

    /**
     * Generates a random question with multiple-choice answers.
     */
    private void generateQuestion() {
        Random random = new Random();
        int randomNumber = random.nextInt(245);
        int rand = random.nextInt(4) + 1;

        // Get a random nation as the firstNation
        firstNation = allNations.get(randomNumber);

        // Display the country name as the question and set random cities as answer choices
        displayQuestionAndAnswers(firstNation, rand);
    }

    /**
     * Displays the trivia question and sets the multiple-choice answers.
     *
     * @param firstNation The first nation for the question.
     * @param rand The random number to determine the order of the answers.
     */
    @SuppressLint("SetTextI18n")
    private void displayQuestionAndAnswers(GeoList firstNation, int rand) {
        triviaGameTextView.setText("What is the capital of " + firstNation.getCountry() + "?");
        Random random = new Random();
        switch (rand) {
            case 1:
                buttonA.setText(allNations.get(random.nextInt(245)).getCity());
                buttonB.setText(allNations.get(random.nextInt(245)).getCity());
                buttonC.setText(firstNation.getCity());
                buttonD.setText(allNations.get(random.nextInt(245)).getCity());
                break;
            case 2:
                buttonA.setText(firstNation.getCity());
                buttonB.setText(allNations.get(random.nextInt(245)).getCity());
                buttonC.setText(allNations.get(random.nextInt(245)).getCity());
                buttonD.setText(allNations.get(random.nextInt(245)).getCity());
                break;
            case 3:
                buttonA.setText(allNations.get(random.nextInt(245)).getCity());
                buttonB.setText(allNations.get(random.nextInt(245)).getCity());
                buttonC.setText(allNations.get(random.nextInt(245)).getCity());
                buttonD.setText(firstNation.getCity());
                break;
            default:
                buttonA.setText(allNations.get(random.nextInt(245)).getCity());
                buttonB.setText(firstNation.getCity());
                buttonC.setText(allNations.get(random.nextInt(245)).getCity());
                buttonD.setText(allNations.get(random.nextInt(245)).getCity());
                break;
        }
    }

    /**
     * Sets the click listeners for the answer buttons.
     */
    private void setAnswerButtonListeners() {
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(buttonA.getText().toString());
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(buttonB.getText().toString());
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(buttonC.getText().toString());
            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(buttonD.getText().toString());
            }
        });
    }

    /**
     * Checks if the selected answer is correct and displays a toast message.
     *
     * @param selectedAnswer The text of the selected answer button.
     */
    private void checkAnswer(String selectedAnswer) {
        if (selectedAnswer.equals(firstNation.getCity())) {
            Toast.makeText(TriviaGames.this, "Correct!", Toast.LENGTH_SHORT).show();
            updateScore(1);
            consecutiveWrongAnswers = 0;

        } else {
            Toast.makeText(TriviaGames.this, "Wrong!", Toast.LENGTH_SHORT).show();
            consecutiveWrongAnswers++;
            if (consecutiveWrongAnswers >= 3) {
                // Decrement score by 1 if wrong three times
                updateScore(-1);
                consecutiveWrongAnswers = 0; // Reset consecutive wrong answers counter
            }
        }
    }

    /**
     * Sets up the list of GeoList items from the JSON array.
     *
     * @param obj The JSON array containing country-city pairs.
     */
    public void setUpGeoArrays(JSONArray obj) {
        for (int i = 0; i < obj.length(); i++) {
            try {
                JSONObject entry = obj.getJSONObject(i);
                String country = entry.getString("country");
                String city = entry.getString("city");
                GeoList geoItem = new GeoList(country, city);
                allNations.add(geoItem);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Updates the score and saves it in SharedPreferences.
     *
     * @param scoreChange The change in score (positive or negative).
     */
    private void updateScore(int scoreChange) {
        // Retrieve current score from SharedPreferences
        int currentScore = sharedPreferences.getInt(SCORE_KEY, 0);
        // Update score
        int updatedScore = currentScore + scoreChange;
        // Save updated score in SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SCORE_KEY, updatedScore);
        editor.apply();
        // Update TextView to display the updated score
        scoreTracker.setText("Score: " + updatedScore);
    }

    /**
     * Loads JSON data from the assets folder.
     *
     * @return The JSON data as a string.
     */
    private String loadJSONFromAsset() {
        String json = "";
        try {
            InputStream is = getAssets().open("new_geo_json.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
