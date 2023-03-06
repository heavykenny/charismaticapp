package com.example.charismaticapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.charismaticapp.R;
import com.example.charismaticapp.logics.Calculator;

import java.text.DecimalFormat;
import java.util.Objects;

public class CalculatorActivity extends AppCompatActivity {
    private final String TAG = "Debug";
    private String displayTotal = "";
    private String firstNumber = "";
    private String secondNumber = "";
    private String method = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Calculator App");
    }

    public void ButtonClick(View view) {
        TextView displayScreen = findViewById(R.id.txtViewDisplay);
        final int buttonId = view.getId();
        Button btn = findViewById(buttonId);

        if (buttonId == R.id.btnAddition) {
            firstNumber = displayTotal;
            displayTotal = "";
            method = "+";
        } else if (buttonId == R.id.btnMultiply) {
            firstNumber = displayTotal;
            displayTotal = "";
            method = "*";
        } else if (buttonId == R.id.btnMinus) {
            firstNumber = displayTotal;
            displayTotal = "";
            method = "-";
        } else if (buttonId == R.id.btnDivide) {
            firstNumber = displayTotal;
            displayTotal = "";
            method = "/";
        } else if (buttonId == R.id.clearBtn) {
            displayTotal = "";
            firstNumber = "";
            secondNumber = "";
        } else if (buttonId == R.id.btnEquals) {
            secondNumber = displayTotal;
            if (!firstNumber.isEmpty() && !secondNumber.isEmpty()) {
                Calculator calculator = new Calculator(firstNumber, secondNumber);
                switch (method) {
                    case "*":
                        displayTotal = String.valueOf(calculator.multiply());
                        break;
                    case "+":
                        displayTotal = String.valueOf(calculator.add());
                        break;
                    case "-":
                        displayTotal = String.valueOf(calculator.minus());
                        break;
                    case "/":
                        displayTotal = String.valueOf(calculator.divide());
                        break;
                }
            }
        } else {
            displayTotal += btn.getText();
        }

        displayScreen.setText(removeTrailingZero(displayTotal));
    }


    public static String removeTrailingZero(String str) {
        if (str.endsWith(".0")) {
            // remove the last .0 from the number
            return str.substring(0, str.length() - 2);
        } else {
            return str;
        }
    }
}