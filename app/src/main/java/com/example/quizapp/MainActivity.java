package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private EditText editTextUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUserName = findViewById(R.id.editTextName);
        Button buttonStartQuiz = findViewById(R.id.buttonStartQuiz);

        buttonStartQuiz.setOnClickListener(v -> {
            String name = editTextUserName.getText().toString().trim();
            if (name.isEmpty()) {
                editTextUserName.setError("Please enter your name");
                return;
            }

            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            intent.putExtra("NAME", name);
            startActivity(intent);
        });
    }
}