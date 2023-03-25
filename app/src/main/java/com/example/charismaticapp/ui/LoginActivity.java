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
import com.example.charismaticapp.logics.UserController;
import com.example.charismaticapp.models.UserModel;
import com.example.charismaticapp.states.CacheManagement;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    UserController userControllerClass;
    private List<UserModel> userData;

    public static List<UserModel> createDummyUserData() {
        List<UserModel> data = new ArrayList<>();

        data.add(new UserModel("Doe", "Johnson", "john", 1234, "pass"));
        data.add(new UserModel("Smith", "Anderson", "jane34", 5678, "abc456"));
        data.add(new UserModel("Brown", "Williams", "mike56", 9012, "xyz789"));
        data.add(new UserModel("Lee", "Taylor", "sarah78", 3456, "qwerty12"));
        data.add(new UserModel("Jones", "Davis", "david90", 7890, "p@ssw0rd"));
        data.add(new UserModel("Taylor", "Moore", "lisa12", 2345, "letmein1"));
        data.add(new UserModel("Wilson", "Johnson", "peter34", 6789, "changeme"));
        data.add(new UserModel("Miller", "Brown", "mary56", 1234, "password123"));
        data.add(new UserModel("Davis", "Smith", "steve78", 5678, "securepass"));
        data.add(new UserModel("Anderson", "Wilson", "emily90", 9012, "passw0rd!"));

        return data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userData = createDummyUserData();
        userControllerClass = new UserController(userData);

        CacheManagement cm = new CacheManagement(getApplicationContext());
        String cacheUserName = cm.readData("username");

        if (cacheUserName != null) {
            Intent intent = new Intent(LoginActivity.this, HomeScreenActivity.class);
            // REFERENCE - https://stackoverflow.com/a/39078856/9332871
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            intent.setExtrasClassLoader(UserModel.class.getClassLoader());
            UserModel userModel = userControllerClass.getUserByUsername(cacheUserName);
            intent.putExtra("UserModel", userModel);
            startActivity(intent);
        }

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
        EditText edtUsername = findViewById(R.id.edtUsername);
        EditText edtPassword = findViewById(R.id.edtPassword);
        TextView txtMessage = findViewById(R.id.txtMessage);

        if (userControllerClass.login(edtUsername.getText().toString(), edtPassword.getText().toString())) {
            UserModel userModel = userControllerClass.getUserByUsername(edtUsername.getText().toString());

            CacheManagement cm = new CacheManagement(getApplicationContext());
            cm.saveData("username", userModel.getUsername());

            Intent intent = new Intent(LoginActivity.this, HomeScreenActivity.class);
            // REFERENCE - https://stackoverflow.com/a/39078856/9332871
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            intent.setExtrasClassLoader(UserModel.class.getClassLoader());
            intent.putExtra("UserModel", userModel);
            startActivity(intent);
        } else {
            txtMessage.setVisibility(View.VISIBLE);
            txtMessage.postDelayed(() -> txtMessage.setVisibility(View.GONE), 5000);
        }
    }

    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }
}