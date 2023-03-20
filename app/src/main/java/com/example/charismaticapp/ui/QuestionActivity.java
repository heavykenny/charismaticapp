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
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.charismaticapp.R;
import com.example.charismaticapp.data.QuestionsData;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
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

        radGrpAnswers.setOnCheckedChangeListener((group, checkedId) -> {
            for (int i = 0; i < group.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) group.getChildAt(i);
                radioButton.setChecked(radioButton.getId() == checkedId);
            }
        });

        questionsDataList = loadQuestions();

        updateQuestion();

        btnPrevious.setOnClickListener(v -> {
            currentQuestionIndex--;
            updateQuestion();
        });

        btnNext.setOnClickListener(v -> {
            currentQuestionIndex++;
            updateQuestion();
        });

        btnSubmit.setOnClickListener(v -> {
            Intent overallIntent = new Intent(QuestionActivity.this, OverallActivity.class);
            overallIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(overallIntent);
        });
    }

    private void updateQuestion() {
        btnPrevious.setEnabled(currentQuestionIndex != 0);
        btnNext.setEnabled(currentQuestionIndex != questionsDataList.size() - 1);
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

    private boolean checkAnswer() {
        // Get the current question and the selected answer
        QuestionsData question = questionsDataList.get(currentQuestionIndex);
        int selectedId = radGrpAnswers.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);

        // Check if the selected answer is correct
        if (selectedRadioButton == null) {
            return false;
        } else {
            return selectedRadioButton.getText().toString().equals(question.getCorrectAnswer());
        }
    }

    private List<QuestionsData> loadQuestions() {
        List<QuestionsData> questionList = new ArrayList<>();
        questionList.add(new QuestionsData(QuestionsData.TYPE_TEXT, "What is the capital of France?", "Paris", "London", "Madrid", "Berlin", "Paris"));
        questionList.add(new QuestionsData(QuestionsData.TYPE_IMAGE, "What animal is shown in the picture?", R.drawable.image_sample, "Cat", "Dog", "Bird", "Fish", "Cat"));
        questionList.add(new QuestionsData(QuestionsData.TYPE_VIDEO, "What is the name of the person in the video?", "https://joy1.videvo.net/videvo_files/video/free/2013-07/large_watermarked/hd0051_preview.mp4", "John", "Peter", "Tom", "Harry", "John"));
        questionList.add(new QuestionsData(QuestionsData.TYPE_TEXT, "What is the largest country in the world?", "Russia", "China", "USA", "Canada", "Russia"));
        questionList.add(new QuestionsData(QuestionsData.TYPE_TEXT, "What is the currency of Japan?", "Yen", "Euro", "Dollar", "Pound", "Yen"));
        return questionList;
    }
}