package com.example.googlemapstrial;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import android.content.Intent;

/**
 * GeminiClass is an activity representing a quiz game using AI-generated questions and answers.
 */
public class GeminiClass extends AppCompatActivity {

    /** TextView to display the generated question. */
    TextView text;

    /** Button to submit the answer. */
    Button submit;

    /** Button to generate a new question. */
    Button generate;

    /** Button to navigate back to the main activity. */
    Button back;

    /** EditText for entering the answer. */
    EditText editText;

    /** The generated question. */
    String question;

    /** The correct answer to the generated question. */
    String answer;

    /**
     * Called when the activity is starting. This is where most initialization should go.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gemini_class);

        // Initialize views and buttons
        text = findViewById(R.id.geminitext);
        submit = findViewById(R.id.testsubmit);
        generate = findViewById(R.id.testgenerate);
        back = findViewById(R.id.goBack);
        editText = findViewById(R.id.editText);

        // Intent to return to the main activity
        Intent intent = new Intent(GeminiClass.this, MainActivity.class);

        // Set click listener for the generate button
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateQuestion();
            }
        });

        // Set click listener for the submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        // Set click listener for the back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

    /**
     * Generates a new question using AI.
     *
     * Source: https://developer.android.com/reference/android/location/Geocoder
     */
    public void generateQuestion() {
        Toast.makeText(this, "Working on it!", Toast.LENGTH_SHORT).show();
        GenerativeModel gm = new GenerativeModel("gemini-pro",
                "AIzaSyArulVC_IsImpG9vRtDy-D8Gi4vOMEDZtc");
        GenerativeModelFutures model = GenerativeModelFutures.from(gm);

        Content content = new Content.Builder()
                .addText("Generate a country capital question in this format: What is the capital of (country)?")
                .build();

        // Generate the question asynchronously
        ListenableFuture<GenerateContentResponse> response = model.generateContent(content);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
                @Override
                public void onSuccess(GenerateContentResponse result) {
                    question = result.getText();
                    text.setText(question);
                    generateAnswer();
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                    text.setText(t.toString());
                }
            }, this.getMainExecutor());
        }
    }

    /**
     * Generates the answer to the current question using AI.
     *
     * Source: https://developer.android.com/reference/android/location/Geocoder
     */
    public void generateAnswer() {
        GenerativeModel gm = new GenerativeModel("gemini-pro",
                "AIzaSyArulVC_IsImpG9vRtDy-D8Gi4vOMEDZtc");
        GenerativeModelFutures model = GenerativeModelFutures.from(gm);

        Content content = new Content.Builder()
                .addText(question)
                .build();

        // Generate the answer asynchronously
        ListenableFuture<GenerateContentResponse> response = model.generateContent(content);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
                @Override
                public void onSuccess(GenerateContentResponse result) {
                    answer = result.getText();
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                    text.setText(t.toString());
                }
            }, this.getMainExecutor());
        }
    }

    /**
     * Checks if the entered answer is correct and displays a toast message.
     *
     * Source: https://developer.android.com/reference/android/location/Geocoder
     */
    public void checkAnswer() {
        if(editText.getText().toString().equals(answer)){
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
        }
    }
}
