package com.example.techjini.recyclerbasics.model;

/**
 * Created by techjini on 8/9/16.
 */
public class Bookmark{

    private String title;
    private String link;
    private Boolean status;

    public Bookmark() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
