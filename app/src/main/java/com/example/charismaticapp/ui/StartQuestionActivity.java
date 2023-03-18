package com.example.charismaticapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.charismaticapp.R;

public class StartQuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_question);

        Button startBtn = findViewById(R.id.btnStartTest);
        startBtn.setOnClickListener(v -> {
            Intent intent = new Intent(StartQuestionActivity.this, QuestionActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }

    public void startQuiz(int itemName, Context context) {

        if (itemName == 1) {
            Intent intent = new Intent(context, StartQuestionActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}