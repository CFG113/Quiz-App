package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private static final String EXTRA_SCORE = "SCORE";
    private static final String EXTRA_NAME = "NAME";

    private TextView textViewFinalScore;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewFinalScore = findViewById(R.id.textViewFinalScore);
        Button buttonTakeNewQuiz = findViewById(R.id.buttonTakeNewQuiz);
        Button buttonFinish = findViewById(R.id.buttonFinish);

        int score = getIntent().getIntExtra(EXTRA_SCORE, 0);
        userName = getIntent().getStringExtra(EXTRA_NAME);
        textViewFinalScore.setText("Score: " + score + "/5");

        buttonTakeNewQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
            intent.putExtra(EXTRA_NAME, userName);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        buttonFinish.setOnClickListener(v -> finishAffinity());
    }
}
