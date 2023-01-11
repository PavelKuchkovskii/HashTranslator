package org.kucher.hashtranslatorservice.config.exceptions.api;

public class AppHashIsLoadingException extends RuntimeException {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }
}
