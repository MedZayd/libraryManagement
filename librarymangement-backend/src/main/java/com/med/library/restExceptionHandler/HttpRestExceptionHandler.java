package com.med.library.restExceptionHandler;

import com.med.library.restExceptionHandler.exception.HttpNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class HttpRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<HttpErrorResponse> handleException(HttpNotFoundException e) {
        HttpErrorResponse httpErrorResponse =
                new HttpErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        e.getMessage(),
                        new Date()
                );
        return new ResponseEntity<>(httpErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<HttpErrorResponse> handleException(Exception e) {
        HttpErrorResponse httpErrorResponse =
                new HttpErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        e.getMessage(),
                        new Date()
                );
        return new ResponseEntity<>(httpErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
