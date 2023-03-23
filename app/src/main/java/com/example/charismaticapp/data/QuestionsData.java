package com.example.charismaticapp.data;

import java.util.List;

public class QuestionsData {
    public static final int TYPE_TEXT = 0;
    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_VIDEO = 2;

    private final int questionType;
    private final String questionText;
    private final String answer1;
    private final String answer2;
    private final String answer3;
    private final String answer4;
    private final String answer;
    private int mImageResourceId;
    private String mVideoUrl;

    private String id;
    private String text;
    private List<String> options;

    public QuestionsData(int type, String questionText, String answer1, String answer2, String answer3, String answer4, String answer) {
        questionType = type;
        this.questionText = questionText;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answer = answer;
    }

    public QuestionsData(int type, String questionText, int imageResourceId, String answer1, String answer2, String answer3, String answer4, String answer) {
        questionType = type;
        this.questionText = questionText;
        mImageResourceId = imageResourceId;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answer = answer;
    }

    public QuestionsData(int type, String questionText, String videoUrl, String answer1, String answer2, String answer3, String answer4, String answer) {
        questionType = type;
        this.questionText = questionText;
        mVideoUrl = videoUrl;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answer = answer;

    }

    public int getQuestionType() {
        return questionType;
    }

    public String getAnswer() {
        return answer;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public void setmImageResourceId(int mImageResourceId) {
        this.mImageResourceId = mImageResourceId;
    }

    public String getmVideoUrl() {
        return mVideoUrl;
    }

    public void setmVideoUrl(String mVideoUrl) {
        this.mVideoUrl = mVideoUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getType() {
        return questionType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public String getVideoUrl() {
        return mVideoUrl;
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
