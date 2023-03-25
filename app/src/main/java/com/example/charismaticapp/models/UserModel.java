package com.example.charismaticapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class UserModel implements Parcelable {
    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
    private String username;
    private String firstName;
    private String lastName;
    private int id;
    private String password;

    public UserModel(String sureName, String lastName, String username, int id, String password) {
        this.username = username;
        this.firstName = sureName;
        this.lastName = lastName;
        this.id = id;
        this.password = password;
    }

    protected UserModel(Parcel in) {
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.id = in.readInt();
        this.password = in.readString();
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeInt(id);
        dest.writeString(password);
    }

    public String getFullName() {
        return this.lastName + " " + this.firstName;
    }
}
