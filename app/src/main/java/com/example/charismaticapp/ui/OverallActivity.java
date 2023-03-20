package com.example.charismaticapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.charismaticapp.R;

public class OverallActivity extends AppCompatActivity {
    Button btnGoHome;
    Button btnSaveScore;

    @Override
    public void onBackPressed() {
        Intent availableQuiz = new Intent(OverallActivity.this, QuizActivity.class);
        // https://developer.android.com/reference/android/content/Intent#FLAG_ACTIVITY_NO_HISTORY
        startActivity(availableQuiz);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall);

        btnGoHome = findViewById(R.id.btnGoHome);
        btnSaveScore = findViewById(R.id.btnSaveScore);

        btnGoHome.setOnClickListener(v -> {
            Intent homeActivity = new Intent(OverallActivity.this, HomeScreenActivity.class);
            // https://developer.android.com/reference/android/content/Intent#FLAG_ACTIVITY_NO_HISTORY
            startActivity(homeActivity);
        });

    }
}