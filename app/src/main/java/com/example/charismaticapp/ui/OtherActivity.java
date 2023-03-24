package com.example.charismaticapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.charismaticapp.R;
import com.example.charismaticapp.models.OtherPagesModel;
import com.example.charismaticapp.adapters.OtherPageRecyclerViewAdapter;
import com.example.charismaticapp.models.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Other Applications");

        List<OtherPagesModel> otherPagesModelList = getOtherPagesData();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        OtherPageRecyclerViewAdapter adapter = new OtherPageRecyclerViewAdapter(otherPagesModelList, getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public List<OtherPagesModel> getOtherPagesData() {
        List<OtherPagesModel> otherPagesModelList = new ArrayList<>();
        otherPagesModelList.add(new OtherPagesModel("Calculator App", R.drawable.calculate));
        otherPagesModelList.add(new OtherPagesModel("Background Changer", R.drawable.wallpaper));
        otherPagesModelList.add(new OtherPagesModel("Dice Roller", R.drawable.casino));
        return otherPagesModelList;
    }

    public void openOtherActivity(String pageName, Context appContext) {

        switch (pageName) {
            case "CalculatorController App":
                Intent i = new Intent(appContext, CalculatorActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                appContext.startActivity(i);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        UserModel userModel = getIntent().getParcelableExtra("UserModel");
        Intent i = new Intent(this, HomeScreenActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        i.setExtrasClassLoader(UserModel.class.getClassLoader());
        i.putExtra("UserModel", userModel);
        startActivity(i);
    }
}