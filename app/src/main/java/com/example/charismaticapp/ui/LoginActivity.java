package com.example.charismaticapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.charismaticapp.R;
import com.example.charismaticapp.data.UserData;
import com.example.charismaticapp.logics.User;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private List<UserData> userData;

    public static List<UserData> createDummyUserData() {
        List<UserData> data = new ArrayList<>();

        data.add(new UserData("Doe", "Johnson", "john", 1234, "pass"));
        data.add(new UserData("Smith", "Anderson", "jane34", 5678, "abc456"));
        data.add(new UserData("Brown", "Williams", "mike56", 9012, "xyz789"));
        data.add(new UserData("Lee", "Taylor", "sarah78", 3456, "qwerty12"));
        data.add(new UserData("Jones", "Davis", "david90", 7890, "p@ssw0rd"));
        data.add(new UserData("Taylor", "Moore", "lisa12", 2345, "letmein1"));
        data.add(new UserData("Wilson", "Johnson", "peter34", 6789, "changeme"));
        data.add(new UserData("Miller", "Brown", "mary56", 1234, "password123"));
        data.add(new UserData("Davis", "Smith", "steve78", 5678, "securepass"));
        data.add(new UserData("Anderson", "Wilson", "emily90", 9012, "passw0rd!"));

        return data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userData = createDummyUserData();

        TextView txtSignedUp = findViewById(R.id.txtSignedUp);
        SpannableString spannableString = new SpannableString("Don't have an account? Sign up here");

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {

                // Handle click event
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                // REFERENCE - https://stackoverflow.com/a/39078856/9332871
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false); // Remove underline
            }
        };

        // Set the clickable span on the text
        spannableString.setSpan(clickableSpan, 23, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the SpannableString on the TextView
        txtSignedUp.setText(spannableString);
        txtSignedUp.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void userLogin(View view) {
        User userClass = new User(userData);
        EditText edtUsername = findViewById(R.id.edtUsername);
        EditText edtPassword = findViewById(R.id.edtPassword);
        TextView txtMessage = findViewById(R.id.txtMessage);

        if (userClass.login(edtUsername.getText().toString(), edtPassword.getText().toString())) {
            Intent intent = new Intent(LoginActivity.this, HomeScreenActivity.class);
            // REFERENCE - https://stackoverflow.com/a/39078856/9332871
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            intent.setExtrasClassLoader(UserData.class.getClassLoader());
            intent.putExtra("UserData", userClass.getUser(edtUsername.getText().toString()));
            startActivity(intent);
        } else {
            txtMessage.setVisibility(View.VISIBLE);
            txtMessage.postDelayed(() -> txtMessage.setVisibility(View.GONE), 5000);
        }
    }

}