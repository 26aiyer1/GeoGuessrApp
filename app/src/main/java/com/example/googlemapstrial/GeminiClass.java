package com.example.googlemapstrial;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import android.content.Intent;
import android.widget.Toast;


public class GeminiClass extends AppCompatActivity {

    TextView text;
    Button submit;

    Button generate;

    Button back;

    EditText editText;

    String question;

    String answer;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gemini_class);

        text = findViewById(R.id.geminitext);
        submit = findViewById(R.id.testsubmit);
        generate = findViewById(R.id.testgenerate);
        back = findViewById(R.id.goBack);
        editText = findViewById(R.id.editText);



        Intent intent = new Intent(GeminiClass.this, MainActivity.class);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateQuestion();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }


    public void generateQuestion(){
        Toast.makeText(this, "Working on it!", Toast.LENGTH_SHORT).show();
        GenerativeModel gm = new GenerativeModel("gemini-pro",
                "AIzaSyArulVC_IsImpG9vRtDy-D8Gi4vOMEDZtc");
        GenerativeModelFutures model = GenerativeModelFutures.from(gm);

        Content content = new Content.Builder()
                .addText("Generate a country capital question in this format: What is the capital of (country)?")
                .build();


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

    public void generateAnswer(){
        GenerativeModel gm = new GenerativeModel("gemini-pro",
                "AIzaSyArulVC_IsImpG9vRtDy-D8Gi4vOMEDZtc");
        GenerativeModelFutures model = GenerativeModelFutures.from(gm);

        Content content = new Content.Builder()
                .addText(question)
                .build();


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


    public void checkAnswer(){
       if(editText.getText().toString().equals(answer)){
           Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
       }
       else{
           Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
       }
    }

}