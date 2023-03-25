package com.example.charismaticapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.charismaticapp.R;
import com.example.charismaticapp.models.UserModel;

public class ProfileActivity extends AppCompatActivity {

    TextView txtName;
    TextView txtUserId;
    TextView txtFullName;
    TextView txtUsername;
    Button btnGoHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        UserModel userModel = getIntent().getParcelableExtra("UserModel");

        txtName = findViewById(R.id.txtName);
        txtUserId = findViewById(R.id.txtUserId);
        txtFullName = findViewById(R.id.txtFullName);
        txtUsername = findViewById(R.id.txtUsername);
        btnGoHome = findViewById(R.id.btnGoHome);

        txtName.setText(userModel.getFullName() + "'s Settings");
        txtUserId.setText(String.valueOf(userModel.getId()));
        txtFullName.setText(userModel.getFullName());
        txtUsername.setText(userModel.getUsername());

        btnGoHome.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, HomeScreenActivity.class);
            // REFERENCE - https://stackoverflow.com/a/39078856/9332871
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            intent.setExtrasClassLoader(UserModel.class.getClassLoader());
            intent.putExtra("UserModel", userModel);
            startActivity(intent);
        });

    }
}