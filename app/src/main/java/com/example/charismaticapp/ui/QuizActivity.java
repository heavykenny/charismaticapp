package com.example.charismaticapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.charismaticapp.R;
import com.example.charismaticapp.models.UserModel;
import com.example.charismaticapp.fragments.QuizFragment;

public class QuizActivity extends AppCompatActivity {

    UserModel userModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        userModel = getIntent().getParcelableExtra("UserModel");

        // Get the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Create a new Fragment instance
        QuizFragment fragment = new QuizFragment(userModel);

        // Add the Fragment to the layout
        fragmentManager.beginTransaction()
                .add(R.id.quizFragment, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        userModel = getIntent().getParcelableExtra("UserModel");
        Intent i = new Intent(this, HomeScreenActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        i.setExtrasClassLoader(UserModel.class.getClassLoader());
        i.putExtra("UserModel", userModel);
        startActivity(i);
    }
}