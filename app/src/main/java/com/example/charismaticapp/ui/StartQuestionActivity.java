package com.example.charismaticapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.charismaticapp.R;
import com.example.charismaticapp.data.QuizCategory;
import com.example.charismaticapp.data.TestListData;
import com.example.charismaticapp.data.UserData;
import com.example.charismaticapp.logics.UtilClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class StartQuestionActivity extends AppCompatActivity {

    UtilClass utilClass = new UtilClass();

    TextView txtHighestScore;
    TextView txtQuestionCount;
    TextView txtTestTitle;
    TextView txtTitle;
    TextView txtDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_question);

        String testId = getIntent().getStringExtra("TestId");

        TestListData testListData = getTestData(testId);

        txtHighestScore = findViewById(R.id.txtHighestScore);
        txtQuestionCount = findViewById(R.id.txtQuestionCount);
        txtDuration = findViewById(R.id.txtDuration);
        txtTitle = findViewById(R.id.txtTitle);
        txtTestTitle = findViewById(R.id.txtTestTitle);

        txtHighestScore.setText("100%");
        txtQuestionCount.setText(testListData.getCount() + " Tests");
        txtDuration.setText(testListData.getDuration() + " minutes");
        txtTestTitle.setText(testListData.getName());
        txtTitle.setText(utilClass.capitalize(testListData.getCatId()));

        Button startBtn = findViewById(R.id.btnStartTest);
        startBtn.setOnClickListener(v -> {
            Intent intent = new Intent(StartQuestionActivity.this, QuestionActivity.class);
            intent.putExtra("TestId", testListData.getTestId());
            UserData userData = getIntent().getParcelableExtra("UserData");
            intent.setExtrasClassLoader(UserData.class.getClassLoader());
            intent.putExtra("UserData", userData);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }

    public TestListData getTestData(String testId) {
        TestListData data = null;
        Gson gson = new Gson();
        String jsonString = utilClass.loadJsonFileFromAssets("testList.json", getApplicationContext());
        Type type = new TypeToken<List<TestListData>>() {
        }.getType();
        List<TestListData> testList = gson.fromJson(jsonString, type);

        for (TestListData test : testList) {
            if (test.getTestId().equals(testId)) {
                data = test;
                break;
            }
        }
        return data;
    }
}