package com.example.springProject.URLShortener;

import jakarta.validation.constraints.Null;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class URLShortenerDAO {

    private JdbcTemplate DBObj;

    public String getURL(String longURL){
        String query = "select shortURL from urlModel where longURL = ?";
        String str = DBObj.queryForObject(query, String.class, longURL);
        if(str != null) return str;
        return "";
    }

    public boolean isLongURL(String longURL){
        String query = "select id from urlModel where longURL = ?";
        Integer id = DBObj.queryForObject(query, Integer.class, longURL);
        if(id != null) return true;
        return false;
    }

    public boolean setURL(String longURL, String shortURL){
        String query = "insert into urlModel (longURL, shortURL) values (?,?)";
        DBObj.update(query, longURL, shortURL);
        if(isLongURL(longURL)) return true;
        return false;
    }
}
