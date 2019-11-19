package com.med.library.utils;

import com.med.library.restExceptionHandler.CustomHttpStatus;
import com.med.library.restExceptionHandler.HttpErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationError {
    public static ResponseEntity<HttpErrorResponse<Map<String, String>>> getErrors(List<ObjectError> allErrors) {
        Map<String, String> errors = new HashMap<>();
        String field, message;
        for (ObjectError objectError : allErrors) {
            field = ((FieldError) objectError).getField();
            message = objectError.getDefaultMessage();
            errors.put(field, message);
        }
        HttpErrorResponse<Map<String, String>> httpErrorResponse =
                new HttpErrorResponse<>(CustomHttpStatus.ERROR, "Bean Validation Errors", new Date(), errors);
        return new ResponseEntity<>(httpErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
