package com.example.charismaticapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.charismaticapp.R;
import com.example.charismaticapp.data.OtherPageData;
import com.example.charismaticapp.adapters.OtherPageRecyclerViewAdapter;
import com.example.charismaticapp.data.UserData;

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
        OtherPageRecyclerViewAdapter adapter = new OtherPageRecyclerViewAdapter(data, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public List<OtherPageData> fill_with_data() {
        List<OtherPageData> data = new ArrayList<>();
        data.add(new OtherPageData("Calculator App", R.drawable.calculate));
        data.add(new OtherPageData("Background Changer", R.drawable.wallpaper));
        data.add(new OtherPageData("Dice Roller", R.drawable.casino));
        return data;
    }

    public void openOtherActivity(String itemName, Context appContext) {

        switch (itemName) {
            case "Calculator App":
                Intent intent = new Intent(appContext, CalculatorActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                appContext.startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        UserData userData = getIntent().getParcelableExtra("UserData");
        Intent i = new Intent(this, HomeScreenActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        i.putExtra("UserData", userData);
        startActivity(i);
    }
}