package com.example.springProject.URLShortener;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class URLShortenerController {
    private URL url;
    private Base62Conversion base62Obj;
    private UniqueIDGenerator uniqueIDObj;
    private URLShortenerDAO urlDAO;

    public URLShortenerController(){
        url = new URL();
        base62Obj = new Base62Conversion();
        uniqueIDObj = new UniqueIDGenerator();
        urlDAO = new URLShortenerDAO();
    }

    @PostMapping(path = "/submit")
    public void postURL( String x){
        URL url = new URL();
        String longURL = "www.google.com";
        url.setLongURL(longURL);
        //Check if the URL present in the Database.
        //If not present in the Database then Update the Database.
        if(urlDAO.isLongURL(longURL)){
            String shortURL = urlDAO.getURL(longURL);
            System.out.println("Already Present -> " + shortURL);
            url.setShortURL(shortURL);
        }
        else{
            uniqueIDObj.setUniqueID();
            System.out.println("ID -> " + uniqueIDObj.getUniqueID());
            base62Obj.convertToBase62(uniqueIDObj.getUniqueID());
            System.out.println("base62 ->" + base62Obj.getBase62Value());
            urlDAO.setURL(longURL, base62Obj.getBase62Value());
            System.out.println("Short URL -> " + urlDAO.getURL(longURL));
        }
//        return ResponseEntity.status(HttpStatus.OK).body(url);
    }
}
