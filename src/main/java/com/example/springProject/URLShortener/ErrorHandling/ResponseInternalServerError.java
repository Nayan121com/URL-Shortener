package com.example.springProject.URLShortener.ErrorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ResponseInternalServerError extends RuntimeException {

    public ResponseInternalServerError(String message){
        super(message);
    }
}
