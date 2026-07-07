package com.project.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler( TaskNotExistException.class)
    public ResponseEntity<ErrorResponse> handleTaskNotExistException( TaskNotExistException e)
    {
        ErrorResponse err = new ErrorResponse(404,"Not found",e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}
