package com.example.charismaticapp.models;

public class QuestionModel {
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_IMAGE = 2;
    public static final int TYPE_VIDEO = 3;

    private final int type;
    private final String questionText;
    private final String answer1;
    private final String answer2;
    private final String answer3;
    private final String answer4;
    private final String answer;
    private String imageURL;
    private String videoUrl;
    private String id;
    private String testId;

    public QuestionModel(String id, String testId, int type, String questionText, String answer1, String answer2, String answer3, String answer4, String answer) {
        this.id = id;
        this.testId = testId;
        this.type = type;
        this.questionText = questionText;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answer = answer;
    }

    public QuestionModel(String id, String testId, int type, String questionText, String imageURL, String videoUrl, String answer1, String answer2, String answer3, String answer4, String answer) {
        this.id = id;
        this.testId = testId;
        this.type = type;
        this.questionText = questionText;
        this.imageURL = imageURL;
        this.videoUrl = videoUrl;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answer = answer;
    }

    public String getTestId() {
        return this.testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getAnswer() {
        return answer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public String getCorrectAnswer() {
        return answer;
    }

}
