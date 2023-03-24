package com.example.charismaticapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.charismaticapp.R;
import com.example.charismaticapp.models.UserModel;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        UserModel userModel = getIntent().getParcelableExtra("UserModel");

        TextView username = findViewById(R.id.txtName);
        username.setText("Welcome, "+ userModel.getLastName() + "!");
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
        intent.setExtrasClassLoader(UserModel.class.getClassLoader());
        UserModel userModel = getIntent().getParcelableExtra("UserModel");
        intent.setExtrasClassLoader(UserModel.class.getClassLoader());
        intent.putExtra("UserModel", userModel);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void showNoteActivity(View view) {
        Intent intent = new Intent(view.getContext(), NoteActivity.class);
        intent.setExtrasClassLoader(UserModel.class.getClassLoader());
        UserModel userModel = getIntent().getParcelableExtra("UserModel");
        intent.putExtra("UserModel", userModel);
        // REFERENCE - https://stackoverflow.com/a/39078856/9332871
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        UserModel userModel = getIntent().getParcelableExtra("UserModel");
        Intent i = new Intent(this, HomeScreenActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        i.setExtrasClassLoader(UserModel.class.getClassLoader());
        i.putExtra("UserModel", userModel);
        startActivity(i);
    }
}