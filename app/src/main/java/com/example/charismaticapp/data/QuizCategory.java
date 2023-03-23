package com.example.charismaticapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class QuizCategory implements Parcelable {
    private String catId;
    private String name;
    private int testCounts;

    public QuizCategory(String catId, String name, int testCounts) {
        this.catId = catId;
        this.name = name;
        this.testCounts = testCounts;
    }

    protected QuizCategory(Parcel in) {
        catId = in.readString();
        name = in.readString();
        testCounts = in.readInt();
    }

    public static final Creator<QuizCategory> CREATOR = new Creator<QuizCategory>() {
        @Override
        public QuizCategory createFromParcel(Parcel in) {
            return new QuizCategory(in);
        }

        @Override
        public QuizCategory[] newArray(int size) {
            return new QuizCategory[size];
        }
    };

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        dest.writeString(name);
        dest.writeInt(testCounts);
    }
}
