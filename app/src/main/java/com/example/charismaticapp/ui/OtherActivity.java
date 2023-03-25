package com.example.charismaticapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.charismaticapp.R;
import com.example.charismaticapp.adapters.OtherPageRecyclerViewAdapter;
import com.example.charismaticapp.models.OtherPagesModel;
import com.example.charismaticapp.models.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OtherActivity extends AppCompatActivity {
    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Other Applications");

        userModel = getIntent().getParcelableExtra("UserModel");

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
        otherPagesModelList.add(new OtherPagesModel("Language Learning", R.drawable.translate));
        return otherPagesModelList;
    }

    public void openOtherActivity(String pageName, Context appContext) {
        Intent i;
        switch (pageName) {
            case "Calculator App":
                i = new Intent(appContext, CalculatorActivity.class);
                break;

            case "Background Changer":
                i = new Intent(appContext, BackgroundChangerActivity.class);
                break;

            case "Dice Roller":
                i = new Intent(appContext, DiceRollingActivity.class);
                break;
            default:
                i = new Intent(appContext, OtherActivity.class);
                break;
        }

        i.setExtrasClassLoader(UserModel.class.getClassLoader());
        i.putExtra("UserModel", userModel);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        appContext.startActivity(i);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, HomeScreenActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        i.setExtrasClassLoader(UserModel.class.getClassLoader());
        i.putExtra("UserModel", userModel);
        startActivity(i);
    }
}