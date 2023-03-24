package com.example.charismaticapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.charismaticapp.R;
import com.example.charismaticapp.models.UserModel;

public class OverallActivity extends AppCompatActivity {
    Button btnGoHome;
    Button btnSaveScore;
    TextView txtOverAllScore;

    UserModel userModel;

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
        userModel = getIntent().getParcelableExtra("UserModel");

        btnGoHome = findViewById(R.id.btnGoHome);
        btnSaveScore = findViewById(R.id.btnSaveScore);
        txtOverAllScore = findViewById(R.id.txtOverAllScore);

        txtOverAllScore.setText(overAll + "%");

        btnGoHome.setOnClickListener(v -> {
            Intent homeActivity = new Intent(OverallActivity.this, HomeScreenActivity.class);
            homeActivity.setExtrasClassLoader(UserModel.class.getClassLoader());
            homeActivity.putExtra("UserModel", userModel);
            // https://developer.android.com/reference/android/content/Intent#FLAG_ACTIVITY_NO_HISTORY
            homeActivity.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(homeActivity);
        });

    }
}