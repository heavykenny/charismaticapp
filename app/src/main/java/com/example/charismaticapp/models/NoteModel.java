package com.example.charismaticapp.models;

public class NoteModel {
    private String createdDate;
    private String noteTitle;

    public NoteModel(String noteTitle, String createdDate) {
        this.noteTitle = noteTitle;
        this.createdDate = createdDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }
}
