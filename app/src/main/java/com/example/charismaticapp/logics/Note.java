package com.example.charismaticapp.logics;

import android.content.Context;

import com.example.charismaticapp.data.NoteData;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Note {
    public NoteData createNote(String fileName, String fileContents, Context context) {
        FileOutputStream outputStream = null;
        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = new File(context.getFilesDir(), fileName);
        return new NoteData(file.getName(), String.valueOf(file.lastModified()));
    }

    public List<NoteData> readAllNotes(Context context) {
        File[] files = context.getFilesDir().listFiles();
        List<NoteData> data = Arrays.stream(files)
                .sorted(Comparator.comparingLong(File::lastModified))
                .map(file -> new NoteData(file.getName(), String.valueOf(file.lastModified())))
                .collect(Collectors.toList());
        Collections.reverse(data);
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

        if (!oldFileName.equals(fileName)) {
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
        } else {
            try (FileOutputStream outputStream = new FileOutputStream(oldFile)) {
                outputStream.write(fileContents.getBytes());
                outputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
