package com.example.springProject.URLShortener;

import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class URL {

    @Setter
    private Long id;

    @Setter
    private String longURL;
    private String shortURL;

    public URL(){
    }

    public URL(String longURL){
        this.longURL = longURL;
    }

    public URL (String longURL, String shortURL){
        this.longURL = longURL;
        this.shortURL = shortURL;
    }

    public Long getId() {
        return this.id;
    }

    public String getLongURL() {
        return this.longURL;
    }

    public String getShortURL() {
        return this.shortURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = "www.shorturl.com/" + shortURL;
    }

    @Override
    public String toString() {
        return "URL{" +
                "id=" + id +
                ", longURL='" + longURL + '\'' +
                ", shortURL='" + shortURL + '\'' +
                '}';
    }
}
