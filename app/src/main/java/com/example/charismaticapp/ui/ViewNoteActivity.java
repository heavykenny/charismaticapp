package com.example.charismaticapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.charismaticapp.R;
import com.example.charismaticapp.logics.NoteController;
import com.example.charismaticapp.models.UserModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ViewNoteActivity extends AppCompatActivity {

    EditText titleText;
    EditText contentText;
    Button cancelBtn;
    Button updateBtn;
    UserModel userModel;

    NoteController noteControllerClass = new NoteController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);

        titleText = findViewById(R.id.edtTitleText);
        contentText = findViewById(R.id.edtContentText);
        cancelBtn = findViewById(R.id.btnCancel);
        updateBtn = findViewById(R.id.btnUpdate);
        userModel = getIntent().getParcelableExtra("UserModel");

        Intent i = getIntent();
        String fileName = i.getStringExtra("fileName");

        cancelBtn.setOnClickListener(v -> onBackPressed());

        updateBtn.setOnClickListener(v -> {
            noteControllerClass.saveNote(fileName, titleText.getText().toString(), contentText.getText().toString(), ViewNoteActivity.this);
            onBackPressed();
        });

        File file = noteControllerClass.getFile(getApplicationContext(), fileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            titleText.setText(fileName);

            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append("\n");
            }
            contentText.setText(content.toString());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent note = new Intent(ViewNoteActivity.this, NoteActivity.class);
        // https://developer.android.com/reference/android/content/Intent#FLAG_ACTIVITY_NO_HISTORY
        note.setExtrasClassLoader(UserModel.class.getClassLoader());
        note.putExtra("UserModel", userModel);
        note.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(note);
    }
}