package com.example.charismaticapp.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.charismaticapp.R;

import java.util.Random;

public class DiceRollingActivity extends AppCompatActivity {

    Random r = new Random();
    private ImageView imgViewFirstDice, imgViewSecondDice;
    private Button btnRollDice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_rolling);

        imgViewFirstDice = findViewById(R.id.imgViewFirstDice);
        imgViewSecondDice = findViewById(R.id.imgViewSecondDice);

        btnRollDice = findViewById(R.id.btnRollDice);

        btnRollDice.setOnClickListener(v -> {
            imgViewFirstDice.setImageResource(getResources().getIdentifier("dice_" + (r.nextInt(6) + 1), "drawable", getPackageName()));
            imgViewSecondDice.setImageResource(getResources().getIdentifier("dice_" + (r.nextInt(6) + 1), "drawable", getPackageName()));
        });
    }
}