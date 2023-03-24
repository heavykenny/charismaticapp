package com.example.charismaticapp.models;

public class OtherPagesModel {

    public String pageName;
    public int imageId;

    public OtherPagesModel(String pageName, int imageId) {
        this.pageName = pageName;
        this.imageId = imageId;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}