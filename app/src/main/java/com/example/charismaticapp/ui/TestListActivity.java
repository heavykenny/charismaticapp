package com.example.charismaticapp.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.charismaticapp.R;
import com.example.charismaticapp.adapters.TestListRecyclerViewAdapter;
import com.example.charismaticapp.models.QuizCategoryModel;
import com.example.charismaticapp.models.TestListModel;
import com.example.charismaticapp.models.UserModel;
import com.example.charismaticapp.logics.UtilClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class TestListActivity extends AppCompatActivity {

    UserModel userModel;
    QuizCategoryModel quizCategoryModel;
    UtilClass utilClass = new UtilClass();
    TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list);
        quizCategoryModel = getIntent().getParcelableExtra("QuizCategoryModel");
        userModel = getIntent().getParcelableExtra("UserModel");

        titleTextView = findViewById(R.id.txtNote);
        titleTextView.setText(utilClass.capitalizeFirstCharacter(quizCategoryModel.getCatId() + " Tests"));
        List<TestListModel> data = fillWithData();

        RecyclerView rvTestList = findViewById(R.id.rvTestList);
        TestListRecyclerViewAdapter adapter = new TestListRecyclerViewAdapter(data, getApplicationContext(), userModel);
        rvTestList.setAdapter(adapter);
        rvTestList.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<TestListModel> fillWithData() {
        List<TestListModel> data;
        // REFERENCE: https://stackoverflow.com/a/60710298/9332871
        Gson gson = new Gson();
        String jsonString = utilClass.getJsonFromStorage("testList.json", getApplicationContext());
        Type type = new TypeToken<List<TestListModel>>() {
        }.getType();
        data = gson.fromJson(jsonString, type);

        data = data.stream()
                .filter(test -> test.getCatId().equals(quizCategoryModel.getCatId()))
                .collect(Collectors.toList());

        return data;
    }
}