package com.example.charismaticapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.charismaticapp.R;
import com.example.charismaticapp.adapters.OtherPageData;
import com.example.charismaticapp.adapters.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Other Applications");


        List<OtherPageData> data = fill_with_data();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(data, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public List<OtherPageData> fill_with_data() {
        List<OtherPageData> data = new ArrayList<>();
        data.add(new OtherPageData("Calculator App", R.color.yellow_green));
        data.add(new OtherPageData("Background Changer", R.color.yellow_green));
        data.add(new OtherPageData("Dice Roller", R.color.yellow_green));
        return data;
    }

    public void openOtherActivityApplication(View view) {

    }
}