package com.activator.quantsapp;

public class FeedModel {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public FeedModel(int id, String name, String imageURL, String status, String profileURL, long time, String url) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
        this.status = status;
        this.profileURL = profileURL;
        this.time = time;
        this.url = url;
    }

    private int id;
    private String name;
    private String imageURL;
    private String status;
    private String profileURL;
    private long time;
    private String url;

}
