package com.example.charismaticapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class QuizAnswers {
    int userId;
    String quizId;
    String answer;

    public QuizAnswers(int userId, String quizId, String answer) {
        this.userId = userId;
        this.quizId = quizId;
        this.answer = answer;
    }

    protected QuizAnswers(Parcel in) {
        userId = in.readInt();
        quizId = in.readString();
        answer = in.readString();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
