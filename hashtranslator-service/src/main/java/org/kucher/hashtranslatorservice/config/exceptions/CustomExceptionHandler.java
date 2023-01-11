package org.kucher.hashtranslatorservice.config.exceptions;

import org.kucher.hashtranslatorservice.config.exceptions.api.AppHashIsLoadingException;
import org.kucher.hashtranslatorservice.config.exceptions.api.AppHashNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = AppHashIsLoadingException.class)
    protected ResponseEntity<String> handleAppHashIsLoaded(RuntimeException ex) {
        String message = "Application is Loading";
        return new ResponseEntity<>(message, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(value = AppHashNotFoundException.class)
    protected ResponseEntity<String> handleAppHashNotFound(RuntimeException ex) {
        String message = "Application not found";
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
