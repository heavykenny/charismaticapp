package com.example.charismaticapp.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.charismaticapp.R;
import com.example.charismaticapp.data.TestListData;
import com.example.charismaticapp.adapters.TestListRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class TestListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list);

        List<TestListData> data = fill_with_data();
        RecyclerView rvTestList = findViewById(R.id.rvTestList);
        TestListRecyclerViewAdapter adapter = new TestListRecyclerViewAdapter(data, getApplication());
        rvTestList.setAdapter(adapter);
        rvTestList.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<TestListData> fill_with_data() {
        List<TestListData> data = new ArrayList<>();
        data.add(new TestListData("Test 1", 1));
        data.add(new TestListData("Test 2", 2));
        data.add(new TestListData("Test 3", 3));
        return data;
    }
}