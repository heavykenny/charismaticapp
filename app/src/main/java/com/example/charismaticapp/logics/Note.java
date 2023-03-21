package com.example.charismaticapp.logics;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.example.charismaticapp.data.NoteData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Note {
    public void createNote(String fileName, String fileContents, Context context) {
        FileOutputStream outputStream;
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
        List<NoteData> data = Arrays.stream(files).sorted((f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified())).map(file -> new NoteData(file.getName(), String.valueOf(file.lastModified()))).collect(Collectors.toList());
        return data;
    }

    public File getFile(Context context, String fileName) {
        return new File(context.getFilesDir(), fileName);
    }

    public void deleteNote(Context context, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        if (file.exists()) file.delete();
    }

    public void saveNote(String oldFileName, String fileName, String fileContents, Context context) {
        File oldFile = new File(context.getFilesDir(), oldFileName);
        File newFile = new File(context.getFilesDir(), fileName);
        if (oldFile.exists()) {
            try (FileOutputStream outputStream = new FileOutputStream(newFile)) {
                outputStream.write(fileContents.getBytes());
                outputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            if (newFile.exists() && newFile.length() == fileContents.length()) {
                oldFile.delete();
            }
        }
    }

}
