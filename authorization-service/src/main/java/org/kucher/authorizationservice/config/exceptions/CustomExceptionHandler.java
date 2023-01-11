package org.kucher.authorizationservice.config.exceptions;

import org.kucher.authorizationservice.config.exceptions.api.JwtTokenGenerationException;
import org.kucher.authorizationservice.config.exceptions.api.UserAlreadyChangedException;
import org.kucher.authorizationservice.config.exceptions.api.UserNotFoundException;
import org.kucher.authorizationservice.config.exceptions.api.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = UserAlreadyChangedException.class)
    protected ResponseEntity<String> handleAppHashIsLoaded(RuntimeException ex) {
        String message = "The user has already been changed";
        return new ResponseEntity<>(message, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    protected ResponseEntity<String> handleUserNotFound(RuntimeException ex) {
        String message = "User not found";
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = WrongPasswordException.class)
    protected ResponseEntity<String> handleWrongPassword(RuntimeException ex) {
        String message = "Wrong password";
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = JwtTokenGenerationException.class)
    protected ResponseEntity<String> handleJwtTokenGeneration(RuntimeException ex) {
        String message = "Something wrong with Token";
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
