package com.project.authentication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleUserNotFoundException(UserNotFoundException e)
    {
        Map<String,Object> map = new HashMap<>();
        map.put("status",400);
        map.put("error","not found");
        map.put("message",e.getMessage());
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserDetailsExistException.class)
    public ResponseEntity<Map<String,Object>> handleUserDetailsExistException(UserDetailsExistException e)
    {
        Map<String,Object> map = new HashMap<>();
        map.put("status",400);
        map.put("error","Bad Request");
        map.put("message",e.getMessage());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}
