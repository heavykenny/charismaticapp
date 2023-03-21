package com.example.charismaticapp.ui;

import android.annotation.SuppressLint;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NoteActivity extends AppCompatActivity {
    Note noteClass = new Note();
    private List<NoteData> noteDataList;
    NoteListRecyclerViewAdapter adapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        TextView txtNote = findViewById(R.id.txtNote);
        UserData userData = getIntent().getParcelableExtra("UserData");
        txtNote.setText(userData.getLastName() + "'s Notes");

        FloatingActionButton addFab = findViewById(R.id.addFab);

        updateList();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        adapter = new NoteListRecyclerViewAdapter(noteDataList, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addFab.setOnClickListener(v -> {
            String currentDateTime = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault()).format(new Date());
            noteClass.createNote(userData.getUsername() + "-" + currentDateTime, "Sample Text", NoteActivity.this);
            updateList();

            Intent intent = getIntent();
            finish();
            startActivity(intent);
            overridePendingTransition(0, 0);
        });



    }

    private void updateList() {
        noteDataList = noteClass.readAllNotes(NoteActivity.this);
    }

    private List<NoteData> getNotes() {

        List<NoteData> data = new ArrayList<>();
        data.add(new NoteData("Sample 1", "31-08-2009"));

        return data;
    }

}