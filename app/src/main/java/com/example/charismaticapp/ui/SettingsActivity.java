package com.example.charismaticapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.charismaticapp.R;
import com.example.charismaticapp.models.UserModel;
import com.example.charismaticapp.states.CacheManagement;

public class SettingsActivity extends AppCompatActivity {

    Button btnLogout;
    Button btnViewProfile;
    TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnLogout = findViewById(R.id.btnLogout);
        btnViewProfile = findViewById(R.id.btnViewProfile);
        txtName = findViewById(R.id.txtName);
        UserModel userModel = getIntent().getParcelableExtra("UserModel");
        txtName.setText(userModel.getLastName() + " " + userModel.getFirstName() + "'s Settings");

        btnLogout.setOnClickListener(v -> {
            // send to Login
            CacheManagement cm = new CacheManagement(getApplicationContext());
            cm.deleteData("username");

            finish();
            Intent i = new Intent(SettingsActivity.this, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(i);
        });

        btnViewProfile.setOnClickListener(v -> {
            Intent i = new Intent(SettingsActivity.this, ProfileActivity.class);
            // REFERENCE - https://stackoverflow.com/a/39078856/9332871
            i.setExtrasClassLoader(UserModel.class.getClassLoader());
            i.putExtra("UserModel", userModel);
            i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);
        });

    }
}