package com.example.charismaticapp.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.charismaticapp.R;
import com.example.charismaticapp.data.QuestionsData;
import com.example.charismaticapp.data.QuizAnswers;
import com.example.charismaticapp.data.TestListData;
import com.example.charismaticapp.data.UserData;
import com.example.charismaticapp.logics.UtilClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionActivity extends AppCompatActivity {
    private final ArrayList<QuizAnswers> quizAnswersList = new ArrayList<>();
    UtilClass utilClass = new UtilClass();
    String testId;
    private ImageView imgQuestion;
    private VideoView vidQuestion;
    private TextView txtQuestion;
    private TextView txtQuestionCount;
    private RadioButton radBtnAns1;
    private RadioButton radBtnAns2;
    private RadioButton radBtnAns3;
    private RadioButton radBtnAns4;
    private RadioGroup radGrpAnswers;
    private Button btnPrevious;
    private Button btnNext;
    private Button btnSubmit;
    private UserData userData;
    private List<QuestionsData> questionsDataList;
    private int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        imgQuestion = findViewById(R.id.imgQuestion);
        vidQuestion = findViewById(R.id.vidQuestion);
        txtQuestion = findViewById(R.id.txtQuestion);
        txtQuestionCount = findViewById(R.id.txtQuestionCount);
        radBtnAns1 = findViewById(R.id.radBtnAns1);
        radBtnAns2 = findViewById(R.id.radBtnAns2);
        radBtnAns3 = findViewById(R.id.radBtnAns3);
        radBtnAns4 = findViewById(R.id.radBtnAns4);
        radGrpAnswers = findViewById(R.id.radGrpAnswers);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        btnSubmit = findViewById(R.id.btnSubmit);

        testId = getIntent().getStringExtra("TestId");
        userData = getIntent().getParcelableExtra("UserData");

        questionsDataList = loadQuestions();

        updateQuestion();

        btnPrevious.setOnClickListener(v -> {
            currentQuestionIndex--;
            updateQuestion();
        });

        btnNext.setOnClickListener(v -> {
            if (radGrpAnswers.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Please select an option!", Toast.LENGTH_SHORT).show();
            } else {
                QuestionsData question = questionsDataList.get(currentQuestionIndex);
                int selectedId = radGrpAnswers.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedId);
                quizAnswersList.add(new QuizAnswers(userData.getId(), question.getId(), selectedRadioButton.getText().toString()));
                currentQuestionIndex++;
                updateQuestion();
            }
        });

        btnSubmit.setOnClickListener(v -> {
            Intent overallIntent = new Intent(QuestionActivity.this, OverallActivity.class);
            overallIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            overallIntent.setExtrasClassLoader(UserData.class.getClassLoader());
            overallIntent.putExtra("UserData", userData);
            overallIntent.putExtra("OverAll", checkAnswer(questionsDataList, quizAnswersList));
            startActivity(overallIntent);
        });
    }

    private void updateQuestion() {
        btnPrevious.setEnabled(currentQuestionIndex != 0);
        btnNext.setEnabled(currentQuestionIndex != questionsDataList.size() - 1);
        btnSubmit.setEnabled(currentQuestionIndex == questionsDataList.size() - 1);

        QuestionsData question = questionsDataList.get(currentQuestionIndex);

        switch (question.getType()) {
            case QuestionsData.TYPE_IMAGE:
                imgQuestion.setVisibility(View.VISIBLE);
                vidQuestion.setVisibility(View.GONE);
                imgQuestion.setImageResource(question.getImageResourceId());
                break;
            case QuestionsData.TYPE_VIDEO:
                imgQuestion.setVisibility(View.GONE);
                vidQuestion.setVisibility(View.VISIBLE);
                vidQuestion.setVideoURI(Uri.parse(question.getVideoUrl()));
                vidQuestion.start();
            default:
                imgQuestion.setVisibility(View.GONE);
                vidQuestion.setVisibility(View.GONE);
        }

        txtQuestion.setText(question.getQuestionText());
        radBtnAns1.setText(question.getAnswer1());
        radBtnAns2.setText(question.getAnswer2());
        radBtnAns3.setText(question.getAnswer3());
        radBtnAns4.setText(question.getAnswer4());
        txtQuestionCount.setText("Question " + (currentQuestionIndex + 1) + " of " + questionsDataList.size());

        radGrpAnswers.clearCheck();
    }

    public double checkAnswer(List<QuestionsData> questionsDataList, ArrayList<QuizAnswers> quizAnswersList) {
        double correct = 0;
        double totalQuestions = questionsDataList.size();
        for (QuizAnswers quizAnswers : quizAnswersList) {
            for (QuestionsData question : questionsDataList) {
                if (quizAnswers.getQuizId().equals(question.getId()) && quizAnswers.getAnswer().equals(question.getAnswer())) {
                    correct++;
                }
            }
        }

        return (correct / totalQuestions) * 100;
    }

    private List<QuestionsData> loadQuestions() {
        List<QuestionsData> questionsData;
        Gson quizJson = new Gson();
        String jsonString = utilClass.loadJsonFileFromAssets("quizzes.json", getApplicationContext());
        questionsData = quizJson.fromJson(jsonString, new TypeToken<List<QuestionsData>>() {
        }.getType());

        return questionsData.stream().filter(quiz -> quiz.getTestId().equals(testId)).collect(Collectors.toList());
    }
}