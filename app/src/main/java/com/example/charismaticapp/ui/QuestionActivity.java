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

import com.bumptech.glide.Glide;
import com.example.charismaticapp.R;
import com.example.charismaticapp.logics.UtilClass;
import com.example.charismaticapp.models.QuestionModel;
import com.example.charismaticapp.models.QuizAnswerModel;
import com.example.charismaticapp.models.UserModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionActivity extends AppCompatActivity {
    private final ArrayList<QuizAnswerModel> quizAnswerModelList = new ArrayList<>();
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
    private UserModel userModel;
    private List<QuestionModel> questionModelList;
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
        userModel = getIntent().getParcelableExtra("UserModel");

        questionModelList = loadQuestions();

        updateQuestion();

        btnPrevious.setOnClickListener(v -> {
            currentQuestionIndex--;
            updateQuestion();
        });

        btnNext.setOnClickListener(v -> {
            if (radGrpAnswers.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Please select an option!", Toast.LENGTH_SHORT).show();
            } else {
                userSelectedQuestion();
                currentQuestionIndex++;
                updateQuestion();
            }
        });

        btnSubmit.setOnClickListener(v -> {
            userSelectedQuestion();

            Intent overallIntent = new Intent(QuestionActivity.this, OverallActivity.class);
            overallIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            overallIntent.setExtrasClassLoader(UserModel.class.getClassLoader());
            overallIntent.putExtra("UserModel", userModel);
            overallIntent.putExtra("OverAll", checkAnswer(questionModelList, quizAnswerModelList));
            startActivity(overallIntent);
        });
    }

    private void userSelectedQuestion() {
        int selectedId = radGrpAnswers.getCheckedRadioButtonId();
        QuestionModel question = questionModelList.get(currentQuestionIndex);
        RadioButton selectedRadioButton = findViewById(selectedId);
        quizAnswerModelList.add(new QuizAnswerModel(userModel.getId(), question.getId(), selectedRadioButton.getText().toString()));
    }

    private void updateQuestion() {
        btnPrevious.setEnabled(currentQuestionIndex != 0);
        btnNext.setEnabled(currentQuestionIndex != questionModelList.size() - 1);
        btnSubmit.setEnabled(currentQuestionIndex == questionModelList.size() - 1);

        QuestionModel question = questionModelList.get(currentQuestionIndex);

        switch (question.getType()) {
            case QuestionModel.TYPE_IMAGE:
                imgQuestion.setVisibility(View.VISIBLE);
                vidQuestion.setVisibility(View.GONE);
                Glide.with(getApplicationContext()).load(question.getImageURL()).into(imgQuestion);
                break;
            case QuestionModel.TYPE_VIDEO:
                imgQuestion.setVisibility(View.GONE);
                vidQuestion.setVideoURI(Uri.parse(question.getVideoUrl()));
                vidQuestion.setVisibility(View.VISIBLE);
                vidQuestion.start();
            default:
                imgQuestion.setVisibility(View.GONE);
        }

        txtQuestion.setText(question.getQuestionText());
        radBtnAns1.setText(question.getAnswer1());
        radBtnAns2.setText(question.getAnswer2());
        radBtnAns3.setText(question.getAnswer3());
        radBtnAns4.setText(question.getAnswer4());
        txtQuestionCount.setText("Question " + (currentQuestionIndex + 1) + " of " + questionModelList.size());

        radGrpAnswers.clearCheck();
    }

    public double checkAnswer(List<QuestionModel> questionModelList, ArrayList<QuizAnswerModel> quizAnswerModelList) {
        double correct = 0;
        double totalQuestions = questionModelList.size();
        for (QuizAnswerModel quizAnswerModel : quizAnswerModelList) {
            for (QuestionModel question : questionModelList) {
                if (quizAnswerModel.getQuizId().equals(question.getId()) && quizAnswerModel.getAnswer().equals(question.getAnswer())) {
                    correct++;
                }
            }
        }

        return (correct / totalQuestions) * 100;
    }

    private List<QuestionModel> loadQuestions() {
        List<QuestionModel> questionsData;
        Gson quizJson = new Gson();
        String jsonString = utilClass.getJsonFromStorage("quizzes.json", getApplicationContext());
        questionsData = quizJson.fromJson(jsonString, new TypeToken<List<QuestionModel>>() {
        }.getType());

        return questionsData.stream().filter(quiz -> quiz.getTestId().equals(testId)).collect(Collectors.toList());
    }
}