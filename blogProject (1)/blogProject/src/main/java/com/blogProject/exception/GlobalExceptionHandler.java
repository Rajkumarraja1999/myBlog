package com.blogProject.exception;

import com.blogProject.payLoad.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@ControllerAdvice

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails>handleResourceNotFound(ResourceNotFound e,WebRequest web){
        ErrorDetails error=new ErrorDetails(new Date(),e.getMessage(),web.getDescription(true));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }
}
