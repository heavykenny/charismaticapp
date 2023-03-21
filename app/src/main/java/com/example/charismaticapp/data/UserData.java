package com.example.charismaticapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class UserData implements Parcelable {
    String username;
    String firstName;
    String lastName;
    int id;
    String password;

    public UserData(String sureName, String lastName, String username, int id, String password) {
        this.username = username;
        this.firstName = sureName;
        this.lastName = lastName;
        this.id = id;
        this.password = password;
    }

    protected UserData(Parcel in) {
        username = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        id = in.readInt();
        password = in.readString();
    }

    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
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
}
