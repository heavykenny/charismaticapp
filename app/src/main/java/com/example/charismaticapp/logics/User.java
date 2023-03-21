package com.example.charismaticapp.logics;

import com.example.charismaticapp.data.UserData;

import java.util.List;

public class User {
    private final List<UserData> data;

    public User(List<UserData> data) {
        this.data = data;
    }

    public boolean login(String username, String password) {
        for (UserData user : this.data) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public UserData getUser(String username) {
        for (UserData user : this.data) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }
}
