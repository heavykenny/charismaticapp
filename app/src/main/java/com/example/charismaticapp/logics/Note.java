package com.example.charismaticapp.logics;

import android.content.Context;

import com.example.charismaticapp.data.NoteData;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Note {
    public void createNote(String fileName, String fileContents, Context context) {
        FileOutputStream outputStream;
        readAllNotes(context);
        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<NoteData> readAllNotes(Context context) {
        File[] files = context.getFilesDir().listFiles();
        List<NoteData> data = Arrays.stream(files)
                .sorted((f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified()))
                .map(file -> new NoteData(file.getName(), String.valueOf(file.lastModified())))
                .collect(Collectors.toList());
        return data;
    }

}
