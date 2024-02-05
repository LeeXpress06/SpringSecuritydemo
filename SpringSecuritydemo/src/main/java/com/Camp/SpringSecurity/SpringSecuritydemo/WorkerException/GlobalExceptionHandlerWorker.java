package com.Camp.SpringSecurity.SpringSecuritydemo.WorkerException;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandlerWorker {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> workernotfound(ResourceNotFoundException exception,
                                                       WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getFieldName(),
                exception.getMessage() );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }





}
