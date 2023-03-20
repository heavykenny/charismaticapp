package com.example.charismaticapp.data;

public class QuizCategory {
    private String catId;
    private String name;
    private int numbers;

    public QuizCategory(String catId, String name, int numbers) {
        this.catId = catId;
        this.name = name;
        this.numbers = numbers;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }
}
