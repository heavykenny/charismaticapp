package com.example.charismaticapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.example.charismaticapp.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        TextView txtLoginIn = findViewById(R.id.txtLoginIn);
        SpannableString spannableString = new SpannableString("Already have an account? Login in here");

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false); // Remove underline
            }
        };

        // Set the clickable span on the text
        spannableString.setSpan(clickableSpan, 25, 38, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the SpannableString on the TextView
        txtLoginIn.setText(spannableString);
        txtLoginIn.setMovementMethod(LinkMovementMethod.getInstance());
    }

}