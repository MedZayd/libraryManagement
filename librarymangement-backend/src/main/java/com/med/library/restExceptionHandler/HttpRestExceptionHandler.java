package com.med.library.restExceptionHandler;

import com.med.library.restExceptionHandler.exception.HttpNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;

@ControllerAdvice
public class HttpRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<HttpErrorResponse<Class<HttpNotFoundException>>> handleException(HttpNotFoundException e) {
        HttpErrorResponse<Class<HttpNotFoundException>> httpErrorResponse =
                new HttpErrorResponse<>(
                        CustomHttpStatus.ERROR,
                        e.getMessage(),
                        new Date(),
                        null
                );
        e.printStackTrace();
        return new ResponseEntity<>(httpErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<HttpErrorResponse<Class<DataIntegrityViolationException>>> handleException(DataIntegrityViolationException e) {
        HttpErrorResponse<Class<DataIntegrityViolationException>> httpErrorResponse =
                new HttpErrorResponse<>(
                        CustomHttpStatus.ERROR,
                        "Data already exist.",
                        new Date(),
                        null
                );
        e.printStackTrace();
        return new ResponseEntity<>(httpErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<HttpErrorResponse<Class<?>>> handleException(Exception e) {
        HttpErrorResponse<Class<?>> httpErrorResponse =
                new HttpErrorResponse<>(
                        CustomHttpStatus.ERROR,
                        e.getLocalizedMessage(),
                        new Date(),
                        null
                );
        e.printStackTrace();
        return new ResponseEntity<>(httpErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
