package com.example.charismaticapp.logics;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class UtilClass {
    public String getJsonFromStorage(String jsonFilePath, Context appContext) {
        String jsonString = null;

        try {
            AssetManager as = appContext.getAssets();
            InputStream is = as.open(jsonFilePath);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    public String capitalizeFirstCharacter(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
