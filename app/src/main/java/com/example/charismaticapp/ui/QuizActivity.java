package com.example.charismaticapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.charismaticapp.R;
import com.example.charismaticapp.data.UserData;
import com.example.charismaticapp.fragments.QuizFragment;

public class QuizActivity extends AppCompatActivity {

    UserData userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        userData = getIntent().getParcelableExtra("UserData");

        // Get the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Create a new Fragment instance
        QuizFragment fragment = new QuizFragment(userData);

        // Add the Fragment to the layout
        fragmentManager.beginTransaction()
                .add(R.id.quizFragment, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        userData = getIntent().getParcelableExtra("UserData");
        Intent i = new Intent(this, HomeScreenActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        i.setExtrasClassLoader(UserData.class.getClassLoader());
        i.putExtra("UserData", userData);
        startActivity(i);
    }
}