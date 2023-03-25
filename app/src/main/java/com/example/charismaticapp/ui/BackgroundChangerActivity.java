package com.example.charismaticapp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.charismaticapp.R;
import com.example.charismaticapp.models.UserModel;

import java.util.Random;

public class BackgroundChangerActivity extends AppCompatActivity {

    Button btnGoHome;
    Button btnChangeBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_changer);
        UserModel userModel = getIntent().getParcelableExtra("UserModel");

        btnGoHome = findViewById(R.id.btnGoHome);
        btnGoHome.setOnClickListener(v -> {
            Intent intent = new Intent(BackgroundChangerActivity.this, HomeScreenActivity.class);
            // REFERENCE - https://stackoverflow.com/a/39078856/9332871
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            intent.setExtrasClassLoader(UserModel.class.getClassLoader());
            intent.putExtra("UserModel", userModel);
            startActivity(intent);
        });

        LinearLayout linearLayout = findViewById(R.id.linLayChangeBack);
        btnChangeBackground = findViewById(R.id.btnChangeBackground);

        btnChangeBackground.setOnClickListener(v -> {
            Random r = new Random();
            int c = Color.argb(255, r.nextInt(256), r.nextInt(256), r.nextInt(256));
            linearLayout.setBackgroundColor(c);
        });
    }
}