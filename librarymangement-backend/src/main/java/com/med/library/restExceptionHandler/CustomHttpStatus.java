package com.med.library.restExceptionHandler;

public enum CustomHttpStatus {
    SUCCESS("SUCCESS"),
    ERROR("ERROR");

    private final String status;

    CustomHttpStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return this.status;
    }
}
