package com.example.charismaticapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.charismaticapp.R;
import com.example.charismaticapp.data.UserData;
import com.example.charismaticapp.logics.Note;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ViewNoteActivity extends AppCompatActivity {

    EditText titleText;
    EditText contentText;
    Button cancelBtn;
    Button updateBtn;
    String oldFileName;

    UserData userData;

    Note noteClass = new Note();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);

        titleText = findViewById(R.id.edtTitleText);
        oldFileName = titleText.getText().toString();
        contentText = findViewById(R.id.edtContentText);
        cancelBtn = findViewById(R.id.btnCancel);
        updateBtn = findViewById(R.id.btnUpdate);
        userData = getIntent().getParcelableExtra("UserData");

        cancelBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ViewNoteActivity.this, NoteActivity.class);
            intent.setExtrasClassLoader(UserData.class.getClassLoader());
            intent.putExtra("UserData", userData);
            // REFERENCE - https://stackoverflow.com/a/39078856/9332871
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        });

        updateBtn.setOnClickListener(v -> {
            noteClass.saveNote(oldFileName, titleText.getText().toString(), contentText.getText().toString(), ViewNoteActivity.this);

            Intent intent = new Intent(ViewNoteActivity.this, NoteActivity.class);
            intent.setExtrasClassLoader(UserData.class.getClassLoader());
            intent.putExtra("UserData", userData);
            // REFERENCE - https://stackoverflow.com/a/39078856/9332871
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        });

        Intent i = getIntent();
        String fileName = i.getStringExtra("fileName");

        File file = noteClass.getFile(getApplicationContext(), fileName);
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
        Intent note = new Intent(ViewNoteActivity.this, NoteActivity.class);
        // https://developer.android.com/reference/android/content/Intent#FLAG_ACTIVITY_NO_HISTORY
        note.setExtrasClassLoader(UserData.class.getClassLoader());
        note.putExtra("UserData", userData);
        note.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(note);
    }
}