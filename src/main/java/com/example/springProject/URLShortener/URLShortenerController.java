package com.example.springProject.URLShortener;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class URLShortenerController {

    private URL url;
    private Base62Conversion base62Obj;
    private UniqueIDGenerator uniqueIDObj;
    @Autowired
    private URLShortenerDAO daoObj;

    public URLShortenerController(){
        url = new URL();
        base62Obj = new Base62Conversion();
        uniqueIDObj = new UniqueIDGenerator();
        daoObj = new URLShortenerDAO();
    }

    @PostMapping("/submit")
    public ResponseEntity<URL> postURL(@RequestBody String longURL){
        url.setLongURL(longURL);
        URL fetchedURL = daoObj.fetch(url);
        if(fetchedURL.getId() != null){
            return new ResponseEntity<>(fetchedURL, HttpStatus.OK);
        }
        else{
            Long id = System.currentTimeMillis();
            uniqueIDObj.setUniqueID();
            UUID uniqueID = uniqueIDObj.getUniqueID();
            base62Obj.convertToBase62(uniqueID);
            String base62Value = base62Obj.getBase62Value();
            base62Obj.convertToBase62(uniqueIDObj.getUniqueID());
            url.setId(id);
            url.setShortURL(base62Value);
            daoObj.save(url);
            return new ResponseEntity<>(url, HttpStatus.OK);
        }
    }
}
