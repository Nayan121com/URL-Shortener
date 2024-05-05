package com.example.springProject.URLShortener;

public class URL {
    private int id;
    private String longURL;
    private String shortURL;

    public URL(){
    }

    public URL(String longURL){
        this.longURL = longURL;
    }

    public int getId() {
        return id;
    }

    public String getLongURL() {
        return longURL;
    }

    public String getShortURL() {
        return shortURL;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLongURL(String longURL) {
        this.longURL = longURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }
}
