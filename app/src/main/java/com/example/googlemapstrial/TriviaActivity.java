package com.example.googlemapstrial;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Collections;
import java.util.List;

public class TriviaActivity extends AppCompatActivity {

    private TextView questionTextView;
    private Button option1Button, option2Button, option3Button;
    private Assistant assistant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        // Initialize UI elements
        questionTextView = findViewById(R.id.TriviaGameTextView);
        option1Button = findViewById(R.id.option1Button);
        option2Button = findViewById(R.id.option2Button);
        option3Button = findViewById(R.id.option3Button);

        // Initialize Assistant class
        assistant = new Assistant();

        // Set up the question and answer options
        setNewQuestion();

        // Set up OnClickListener for the options
        option1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(option1Button.getText().toString());
            }
        });

        option2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(option2Button.getText().toString());
            }
        });

        option3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(option3Button.getText().toString());
            }
        });
    }

    private void setNewQuestion() {
        // Generate a question and answer options
        String question = "What is the capital of France?";
        List<String> answerOptions = assistant.generateAnswerOptions("Paris");

        // Shuffle the options for randomness
        Collections.shuffle(answerOptions);

        // Display the question and options
        questionTextView.setText(question);
        option1Button.setText(answerOptions.get(0));
        option2Button.setText(answerOptions.get(1));
        option3Button.setText(answerOptions.get(2));
    }

    private void checkAnswer(String selectedAnswer) {
        String correctAnswer = "Paris"; // Correct answer for the example question

        if (selectedAnswer.equals(correctAnswer)) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect. The correct answer is " + correctAnswer, Toast.LENGTH_SHORT).show();
        }

        // Set up a new question after the user answers
        setNewQuestion();
    }
}
