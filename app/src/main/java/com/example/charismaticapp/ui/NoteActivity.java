package com.example.charismaticapp.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.charismaticapp.R;
import com.example.charismaticapp.adapters.NoteListRecyclerViewAdapter;
import com.example.charismaticapp.data.NoteData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity {
    List<NoteData> noteDataList;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        noteDataList = getNotes();

        FloatingActionButton addFab = findViewById(R.id.addFab);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        NoteListRecyclerViewAdapter adapter = new NoteListRecyclerViewAdapter(noteDataList, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addFab.setOnClickListener(v -> {
            noteDataList.add(new NoteData("Add More " + (noteDataList.size() + 1), "31-08-2009"));
            adapter.notifyDataSetChanged();
        });
    }

    private List<NoteData> getNotes() {
        List<NoteData> data = new ArrayList<>();
        data.add(new NoteData("Sample 1", "31-08-2009"));
        data.add(new NoteData("sample 1 sample 1 sample 1 sample 1 sample 1", "20-09-2008"));
        data.add(new NoteData("sample 2", "31-08-2009"));
        data.add(new NoteData("sample 3", "23-11-2010"));
        data.add(new NoteData("sample 4", "16-08-2011"));

        return data;
    }

}