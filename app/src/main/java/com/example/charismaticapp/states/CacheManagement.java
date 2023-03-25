package com.example.charismaticapp.states;


import android.content.Context;
import android.content.SharedPreferences;

public class CacheManagement {
    Context appContext;
    SharedPreferences sharedPreferences;

    public CacheManagement(Context appContext) {
        this.appContext = appContext;
        this.sharedPreferences = appContext.getSharedPreferences("charismatic_cache", Context.MODE_PRIVATE);
    }

    public String readData(String dataKey) {
        return sharedPreferences.getString(dataKey, null);
    }

    public void saveData(String key, String data) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, data);
        editor.apply();
    }

    public void deleteData(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }
}
