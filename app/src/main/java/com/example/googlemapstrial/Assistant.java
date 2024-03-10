package com.example.googlemapstrial;

import java.util.ArrayList;
import java.util.List;

public class Assistant {

    public List<String> generateAnswerOptions(String correctAnswer) {
        List<String> answerOptions = new ArrayList<>();

        // Add the correct answer
        answerOptions.add(correctAnswer);

        // Generate two random incorrect answers (you can modify this logic based on your needs)
        String incorrectAnswer1 = generateRandomAnswer();
        String incorrectAnswer2 = generateRandomAnswer();

        // Ensure that incorrect answers are unique
        while (incorrectAnswer1.equals(correctAnswer) || incorrectAnswer1.equals(incorrectAnswer2)) {
            incorrectAnswer1 = generateRandomAnswer();
        }
        while (incorrectAnswer2.equals(correctAnswer) || incorrectAnswer2.equals(incorrectAnswer1)) {
            incorrectAnswer2 = generateRandomAnswer();
        }

        // Add incorrect answers to the list
        answerOptions.add(incorrectAnswer1);
        answerOptions.add(incorrectAnswer2);

        return answerOptions;
    }

    private String generateRandomAnswer() {
        // This is a placeholder. You might want to implement a more sophisticated logic.
        // For example, fetching random answers from a predefined list, database, or an API.
        return "Random Answer";
    }
}
