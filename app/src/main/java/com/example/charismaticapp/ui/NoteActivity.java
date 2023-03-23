package com.example.charismaticapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.charismaticapp.R;
import com.example.charismaticapp.adapters.NoteListRecyclerViewAdapter;
import com.example.charismaticapp.data.NoteData;
import com.example.charismaticapp.data.UserData;
import com.example.charismaticapp.logics.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NoteActivity extends AppCompatActivity {
    Note noteClass = new Note();
    NoteListRecyclerViewAdapter adapter;
    UserData userData;
    Intent intent;
    private List<NoteData> noteDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        intent = getIntent();

        TextView txtNote = findViewById(R.id.txtNote);
        userData = getIntent().getParcelableExtra("UserData");
        txtNote.setText(userData.getLastName() + "'s Notes");

        FloatingActionButton addFab = findViewById(R.id.addFab);

        updateList();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        adapter = new NoteListRecyclerViewAdapter(noteDataList, getApplicationContext(), getIntent());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addFab.setOnClickListener(v -> {
            String currentDateTime = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault()).format(new Date());
            String fileName = userData.getUsername() + "-" + currentDateTime + ".txt";
            NoteData newNote = noteClass.createNote(fileName, "Sample Text", NoteActivity.this);
            noteDataList.add(0, newNote);
            adapter.notifyItemRangeInserted(0, 1);
            recyclerView.smoothScrollToPosition(0);
        });

    }

    private void updateList() {
        noteDataList = noteClass.readAllNotes(NoteActivity.this);
    }

    public void viewNoteDetails(Context appContext, String fileName, UserData i) {
        Intent intent = new Intent(appContext, ViewNoteActivity.class);
        intent.putExtra("fileName", fileName);
        intent.putExtra("UserData", i);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        appContext.startActivity(intent);
    }

    public void deleteNoteDetails(Context appContext, String fileName, UserData user) {
        noteClass.deleteNote(appContext, fileName);
        Toast.makeText(appContext, "Note Deleted ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent note = new Intent(NoteActivity.this, HomeScreenActivity.class);
        // https://developer.android.com/reference/android/content/Intent#FLAG_ACTIVITY_NO_HISTORY
        note.setExtrasClassLoader(UserData.class.getClassLoader());
        note.putExtra("UserData", userData);
        note.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(note);
    }
}