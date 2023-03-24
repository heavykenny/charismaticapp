package com.example.charismaticapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.charismaticapp.R;
import com.example.charismaticapp.models.TestListModel;
import com.example.charismaticapp.models.UserModel;
import com.example.charismaticapp.logics.UtilClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

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

        TestListModel testListModel = getTestData(testId);

        txtHighestScore = findViewById(R.id.txtHighestScore);
        txtQuestionCount = findViewById(R.id.txtQuestionCount);
        txtDuration = findViewById(R.id.txtDuration);
        txtTitle = findViewById(R.id.txtTitle);
        txtTestTitle = findViewById(R.id.txtTestTitle);

        txtHighestScore.setText("100%");
        txtQuestionCount.setText(testListModel.getCount() + " Questions");
        txtDuration.setText(testListModel.getDuration() + " minutes");
        txtTestTitle.setText(testListModel.getName());
        txtTitle.setText(utilClass.capitalizeFirstCharacter(testListModel.getCatId()));

        Button startBtn = findViewById(R.id.btnStartTest);
        startBtn.setOnClickListener(v -> {
            Intent intent = new Intent(StartQuestionActivity.this, QuestionActivity.class);
            intent.putExtra("TestId", testListModel.getTestId());
            UserModel userModel = getIntent().getParcelableExtra("UserModel");
            intent.setExtrasClassLoader(UserModel.class.getClassLoader());
            intent.putExtra("UserModel", userModel);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }

    public TestListModel getTestData(String testId) {
        TestListModel data = null;
        Gson gson = new Gson();
        String jsonString = utilClass.getJsonFromStorage("testList.json", getApplicationContext());
        Type type = new TypeToken<List<TestListModel>>() {
        }.getType();
        List<TestListModel> testList = gson.fromJson(jsonString, type);

        for (TestListModel test : testList) {
            if (test.getTestId().equals(testId)) {
                data = test;
                break;
            }
        }
        return data;
    }
}