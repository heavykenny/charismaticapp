package com.example.charismaticapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.charismaticapp.R;
import com.example.charismaticapp.adapters.NoteListRecyclerViewAdapter;
import com.example.charismaticapp.models.NoteModel;
import com.example.charismaticapp.models.UserModel;
import com.example.charismaticapp.logics.NoteController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NoteActivity extends AppCompatActivity {
    NoteController noteControllerClass = new NoteController();
    NoteListRecyclerViewAdapter adapter;
    UserModel userModel;
    Intent intent;
    private List<NoteModel> noteModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        intent = getIntent();

        TextView txtNote = findViewById(R.id.txtNote);
        userModel = getIntent().getParcelableExtra("UserModel");
        txtNote.setText(userModel.getLastName() + "'s Notes");

        FloatingActionButton addFab = findViewById(R.id.addFab);

        updateList();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        adapter = new NoteListRecyclerViewAdapter(noteModelList, getApplicationContext(), getIntent());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addFab.setOnClickListener(v -> {
            String currentDateTime = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault()).format(new Date());
            String fileName = userModel.getUsername() + "-" + currentDateTime + ".txt";
            NoteModel newNote = noteControllerClass.createNote(fileName, "Sample Text", NoteActivity.this);
            noteModelList.add(0, newNote);
            adapter.notifyItemRangeInserted(0, 1);
            recyclerView.smoothScrollToPosition(0);
        });
    }

    private void updateList() {
        noteModelList = noteControllerClass.readAllNotes(NoteActivity.this);
    }

    public void viewNoteDetails(Context appContext, String fileName, UserModel userModel) {
        Intent i = new Intent(appContext, ViewNoteActivity.class);
        i.putExtra("fileName", fileName);
        i.setExtrasClassLoader(UserModel.class.getClassLoader());
        i.putExtra("UserModel", userModel);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        appContext.startActivity(i);
    }

    public void deleteNoteDetails(Context appContext, String fileName) {
        noteControllerClass.deleteNote(appContext, fileName);
        Toast.makeText(appContext, "Note Deleted ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent note = new Intent(NoteActivity.this, HomeScreenActivity.class);
        // https://developer.android.com/reference/android/content/Intent#FLAG_ACTIVITY_NO_HISTORY
        note.setExtrasClassLoader(UserModel.class.getClassLoader());
        note.putExtra("UserModel", userModel);
        note.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(note);
    }
}