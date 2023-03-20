package com.example.charismaticapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.charismaticapp.R;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    public void showOtherActivity(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, OtherActivity.class);
        // REFERENCE - https://stackoverflow.com/a/39078856/9332871
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void showQuizActivity(View view) {
        Intent intent = new Intent(view.getContext(), QuizActivity.class);
        // REFERENCE - https://stackoverflow.com/a/39078856/9332871
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void showNoteActivity(View view) {
        Intent intent = new Intent(view.getContext(), NoteActivity.class);
        // REFERENCE - https://stackoverflow.com/a/39078856/9332871
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
}