package com.example.charismaticapp.logics;

import android.content.Context;

import com.example.charismaticapp.models.NoteModel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class NoteController {
    public NoteModel createNote(String notePath, String fileContents, Context appContext) {
        FileOutputStream os;
        try {
            os = appContext.openFileOutput(notePath, Context.MODE_PRIVATE);
            os.write(fileContents.getBytes());
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        File note = new File(appContext.getFilesDir(), notePath);
        return new NoteModel(note.getName(), String.valueOf(note.lastModified()));
    }

    public List<NoteModel> readAllNotes(Context appContext) {
        File[] allNotes = appContext.getFilesDir().listFiles();
        List<NoteModel> data = Arrays.stream(allNotes).sorted(Comparator.comparingLong(File::lastModified)).map(note -> new NoteModel(note.getName(), String.valueOf(note.lastModified()))).collect(Collectors.toList());
        Collections.reverse(data);
        return data;
    }

    public File getFile(Context appContext, String notePath) {
        return new File(appContext.getFilesDir(), notePath);
    }

    public void deleteNote(Context appContext, String notePath) {
        File note = new File(appContext.getFilesDir(), notePath);
        if (note.exists()) note.delete();
    }

    public void saveNote(String oldFileName, String notePath, String fileContents, Context appContext) {
        File oldFile = new File(appContext.getFilesDir(), oldFileName);
        File newFile = new File(appContext.getFilesDir(), notePath);

        if (!oldFileName.equals(notePath)) {
            if (oldFile.exists()) {
                try (FileOutputStream os = new FileOutputStream(newFile)) {
                    os.write(fileContents.getBytes());
                    os.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                if (newFile.exists() && newFile.length() == fileContents.length()) {
                    oldFile.delete();
                }
            }
        } else {
            try (FileOutputStream os = new FileOutputStream(oldFile)) {
                os.write(fileContents.getBytes());
                os.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
