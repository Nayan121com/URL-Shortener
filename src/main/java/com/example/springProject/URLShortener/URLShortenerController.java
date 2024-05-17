package com.example.springProject.URLShortener;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        //Checks if LongUrl Already present in the Database
        url.setLongURL(longURL);
        URL fetchedURL = daoObj.fetch(url);
        if(fetchedURL.getId() != null){
            return new ResponseEntity<>(fetchedURL, HttpStatus.OK);
        }
        else{
            //Stored UniqueId as TimeStamp for primary key in table URLMODEL
            Long id = System.currentTimeMillis();
            uniqueIDObj.setUniqueID();
            //Creates Universal UniqueId.
            UUID uniqueID = uniqueIDObj.getUniqueID();
            //Converts Universal UniqueId into Base62.
            base62Obj.convertToBase62(uniqueID);
            //GetBase62 Value
            String base62Value = base62Obj.getBase62Value();
            url.setId(id);
            url.setShortURL(base62Value);
            //save url to database
            daoObj.save(url);
            return new ResponseEntity<>(url, HttpStatus.OK);
        }
    }
    @GetMapping("/{shortURL}")
    public ResponseEntity<Object> getURL(@PathVariable String shortURL, HttpServletResponse response){
        try{
            url.setShortURL(shortURL);
            //Fetch OriginalUrl from Database
            URL url1 = daoObj.fetchShort(url);
            String fullUrl = url1.getLongURL();
            if (fullUrl != null) {
                // Ensure the URL is complete
                if (!fullUrl.startsWith("http://") && !fullUrl.startsWith("https://")) {
                    fullUrl = "http://" + fullUrl;
                }
                // Redirect to the full URL
                response.sendRedirect(fullUrl);
            }
        }
        catch (Exception e){
            throw new ResponseURLNotFound(e.getMessage());
        }
        finally {
            return null;
        }
    }

}
