package com.example.springProject.URLShortener.ErrorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResponseURLNotFound extends RuntimeException {

    public ResponseURLNotFound(String message){
        super(message);
    }
}
