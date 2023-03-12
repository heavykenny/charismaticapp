package com.example.charismaticapp.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.charismaticapp.R;

import java.util.Objects;

public class QuizActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Get the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Create a new Fragment instance
        QuizFragment fragment = new QuizFragment();

        // Add the Fragment to the layout
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
    }
}