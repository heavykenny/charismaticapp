package com.example.charismaticapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.charismaticapp.R;

public class OverallActivity extends AppCompatActivity {
    Button btnGoHome;
    Button btnSaveScore;
    TextView txtOverAllScore;

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
        Double overAll = getIntent().getDoubleExtra("OverAll", 0.0);

        btnGoHome = findViewById(R.id.btnGoHome);
        btnSaveScore = findViewById(R.id.btnSaveScore);

        txtOverAllScore = findViewById(R.id.txtOverAllScore);

        txtOverAllScore.setText(overAll + "%");

        btnGoHome.setOnClickListener(v -> {
            Intent homeActivity = new Intent(OverallActivity.this, HomeScreenActivity.class);
            // https://developer.android.com/reference/android/content/Intent#FLAG_ACTIVITY_NO_HISTORY
            startActivity(homeActivity);
        });

    }
}