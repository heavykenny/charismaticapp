package com.example.charismaticapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.charismaticapp.R;
import com.example.charismaticapp.models.UserModel;
import com.example.charismaticapp.states.CacheManagement;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        UserModel userModel = getIntent().getParcelableExtra("UserModel");

        TextView username = findViewById(R.id.txtName);
        username.setText("Welcome, " + userModel.getLastName() + "!");
    }

    public void showOtherActivity(View view) {
        Intent i = new Intent(HomeScreenActivity.this, OtherActivity.class);
        // REFERENCE - https://stackoverflow.com/a/39078856/9332871
        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(i);
    }

    public void showQuizActivity(View view) {
        Intent i = new Intent(view.getContext(), QuizActivity.class);
        // REFERENCE - https://stackoverflow.com/a/39078856/9332871
        i.setExtrasClassLoader(UserModel.class.getClassLoader());
        UserModel userModel = getIntent().getParcelableExtra("UserModel");
        i.putExtra("UserModel", userModel);
        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(i);
    }

    public void showNoteActivity(View view) {
        Intent i = new Intent(view.getContext(), NoteActivity.class);
        i.setExtrasClassLoader(UserModel.class.getClassLoader());
        UserModel userModel = getIntent().getParcelableExtra("UserModel");
        i.putExtra("UserModel", userModel);
        // REFERENCE - https://stackoverflow.com/a/39078856/9332871
        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(i);
    }


    public void showSettingActivity(View view) {
        Intent i = new Intent(view.getContext(), SettingsActivity.class);
        i.setExtrasClassLoader(UserModel.class.getClassLoader());
        UserModel userModel = getIntent().getParcelableExtra("UserModel");
        i.putExtra("UserModel", userModel);
        // REFERENCE - https://stackoverflow.com/a/39078856/9332871
        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        CacheManagement cm = new CacheManagement(getApplicationContext());
        String cacheUserName = cm.readData("username");
        if (cacheUserName == null) {
            finish();
        }
    }
}