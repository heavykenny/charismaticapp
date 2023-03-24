package com.example.charismaticapp.logics;

import com.example.charismaticapp.models.UserModel;

import java.util.List;

public class UserController {
    private List<UserModel> userModelList;

    public UserController(List<UserModel> userModelList) {
        this.userModelList = userModelList;
    }

    public boolean login(String username, String password) {
        for (UserModel user : this.userModelList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public UserModel getUserByUsername(String username) {
        for (UserModel user : this.userModelList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }
}
