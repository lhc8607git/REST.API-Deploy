package com.example.restfulwebservice.exception;


// 2xx
// 4xx 클라이언트
// 5xx 서버

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)   // NOT_FOUND = 404
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super((message));
    }
}
