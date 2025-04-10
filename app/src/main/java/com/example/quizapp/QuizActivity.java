package com.example.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private static final String EXTRA_SCORE = "SCORE";

    private TextView textViewQuestion;
    private Button[] answerButtons = new Button[4];
    private ProgressBar progressBar;

    private List<Question> questions = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        initViews();
        initializeQuestions();
        displayQuestion();
        setupAnswerClickListeners();
    }

    private void initViews() {
        textViewQuestion = findViewById(R.id.questionTextView);
        answerButtons[0] = findViewById(R.id.button1);
        answerButtons[1] = findViewById(R.id.button2);
        answerButtons[2] = findViewById(R.id.button3);
        answerButtons[3] = findViewById(R.id.button4);
        progressBar = findViewById(R.id.progressBar);
    }

    private void initializeQuestions() {
        questions.add(new Question("How many bits are there in 2 bytes?", new String[]{"8", "16", "32", "64"}, "16"));
        questions.add(new Question("What does 'CPU' stand for?", new String[]{"Central Processing Unit", "Computer Personal Unit", "Central Power Unit", "Computer Processing Utility"}, "Central Processing Unit"));
        questions.add(new Question("What is an avocado classified as in food science?", new String[]{"Vegetable", "Drupe", "Berry", "Nut"}, "Berry"));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Jupiter", "Venus"}, "Mars"));
        questions.add(new Question("What is the smallest prime number?", new String[]{"0", "1", "2", "3"}, "2"));

        progressBar.setMax(questions.size());
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question current = questions.get(currentQuestionIndex);
            textViewQuestion.setText(current.getQuestion());

            for (int i = 0; i < answerButtons.length; i++) {
                answerButtons[i].setText(current.getOptions()[i]);
                answerButtons[i].setBackgroundColor(Color.LTGRAY);
            }

            progressBar.setProgress(currentQuestionIndex + 1);
        } else {
            finishQuiz();
        }
    }

    private void setupAnswerClickListeners() {
        for (int i = 0; i < answerButtons.length; i++) {
            int index = i;
            answerButtons[i].setOnClickListener(v -> checkAnswer(answerButtons[index].getText().toString(), answerButtons[index]));
        }
    }

    private void checkAnswer(String selectedAnswer, Button selectedButton) {
        Question current = questions.get(currentQuestionIndex);
        boolean isCorrect = selectedAnswer.equals(current.getCorrectAnswer());

        selectedButton.setBackgroundColor(isCorrect ? Color.GREEN : Color.RED);
        if (isCorrect) score++;

        new Handler().postDelayed(() -> {
            currentQuestionIndex++;
            displayQuestion();
        }, 1000);
    }

    private void finishQuiz() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(EXTRA_SCORE, score);
        startActivity(intent);
        finish();
    }
}
