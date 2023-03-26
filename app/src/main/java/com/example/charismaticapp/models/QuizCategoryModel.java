package com.example.charismaticapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

// REFERENCES: https://developer.android.com/reference/android/os/Parcelable
public class QuizCategoryModel implements Parcelable {
    private String catId;
    private String quizCategoryTitle;
    private int testCounts;

    public QuizCategoryModel(String catId, String quizCategoryTitle, int testCounts) {
        this.catId = catId;
        this.quizCategoryTitle = quizCategoryTitle;
        this.testCounts = testCounts;
    }

    protected QuizCategoryModel(Parcel in) {
        catId = in.readString();
        quizCategoryTitle = in.readString();
        testCounts = in.readInt();
    }

    public static final Creator<QuizCategoryModel> CREATOR = new Creator<QuizCategoryModel>() {
        @Override
        public QuizCategoryModel createFromParcel(Parcel in) {
            return new QuizCategoryModel(in);
        }

        @Override
        public QuizCategoryModel[] newArray(int size) {
            return new QuizCategoryModel[size];
        }
    };

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getQuizCategoryTitle() {
        return quizCategoryTitle;
    }

    public void setQuizCategoryTitle(String quizCategoryTitle) {
        this.quizCategoryTitle = quizCategoryTitle;
    }

    public int getTestCounts() {
        return testCounts;
    }

    public void setTestCounts(int testCounts) {
        this.testCounts = testCounts;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(catId);
        dest.writeString(quizCategoryTitle);
        dest.writeInt(testCounts);
    }
}
