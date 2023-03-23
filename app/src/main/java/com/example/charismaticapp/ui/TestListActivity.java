package com.example.charismaticapp.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.charismaticapp.R;
import com.example.charismaticapp.adapters.TestListRecyclerViewAdapter;
import com.example.charismaticapp.data.QuizCategory;
import com.example.charismaticapp.data.TestListData;
import com.example.charismaticapp.logics.UtilClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class TestListActivity extends AppCompatActivity {

    QuizCategory quizCategory;
    UtilClass utilClass = new UtilClass();
    TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list);
        quizCategory = getIntent().getParcelableExtra("QuizCategory");

        titleTextView = findViewById(R.id.txtNote);
        titleTextView.setText(utilClass.capitalize(quizCategory.getCatId() + " Tests"));
        List<TestListData> data = fill_with_data();
        RecyclerView rvTestList = findViewById(R.id.rvTestList);
        TestListRecyclerViewAdapter adapter = new TestListRecyclerViewAdapter(data, getApplication());
        rvTestList.setAdapter(adapter);
        rvTestList.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<TestListData> fill_with_data() {
        List<TestListData> data;
        Gson gson = new Gson();
        String jsonString = utilClass.loadJsonFileFromAssets("testList.json", getApplicationContext());
        Type type = new TypeToken<List<TestListData>>() {
        }.getType();
        data = gson.fromJson(jsonString, type);

        data = data.stream()
                .filter(test -> test.getCatId().equals(quizCategory.getCatId()))
                .collect(Collectors.toList());

        return data;
    }
}