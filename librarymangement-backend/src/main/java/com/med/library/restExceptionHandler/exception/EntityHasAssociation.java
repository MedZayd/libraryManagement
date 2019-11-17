package com.med.library.restExceptionHandler.exception;

public class EntityHasAssociation extends RuntimeException {
    public EntityHasAssociation(String message) {
        super(message);
    }
}
