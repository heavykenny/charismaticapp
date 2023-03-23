package com.example.charismaticapp.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveState {

    Context appContext;
    String saveName;
    SharedPreferences sp;

    public SaveState(Context appContext, String saveName) {
        this.appContext = appContext;
        this.saveName = saveName;
        sp = appContext.getSharedPreferences(saveName, appContext.MODE_PRIVATE);
    }

    public int getState() {
        return sp.getInt("Key", 0);
    }

    public void setState(int key) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("Key", key);
        editor.apply();
    }
}
