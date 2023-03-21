package com.example.charismaticapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.charismaticapp.R;
import com.example.charismaticapp.data.UserData;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        UserData userData = getIntent().getParcelableExtra("UserData");

        TextView username = findViewById(R.id.txtName);
        username.setText("Welcome, "+ userData.getLastName() + "!");
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
        intent.setExtrasClassLoader(UserData.class.getClassLoader());
        UserData userData = getIntent().getParcelableExtra("UserData");
        intent.putExtra("UserData", userData);
        // REFERENCE - https://stackoverflow.com/a/39078856/9332871
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        UserData userData = getIntent().getParcelableExtra("UserData");
        Intent i = new Intent(this, HomeScreenActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        i.putExtra("UserData", userData);
        startActivity(i);
    }
}