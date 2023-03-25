package com.example.charismaticapp.models;

public class TestListModel {
    private String name;
    private String catId;
    private String testId;
    private int count;
    private String duration;

    public TestListModel(String name, String catId, String testId, int count, String duration) {
        this.name = name;
        this.catId = catId;
        this.testId = testId;
        this.count = count;
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
