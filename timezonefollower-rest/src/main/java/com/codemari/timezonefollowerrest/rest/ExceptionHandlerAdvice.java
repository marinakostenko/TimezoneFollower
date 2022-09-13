package com.codemari.timezonefollowerrest.rest;

import com.codemari.timezonefollowerrest.exception.DuplicatedUserException;
import com.codemari.timezonefollowerrest.exception.LocationNotFoundException;
import com.codemari.timezonefollowerrest.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundHandler(UserNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DuplicatedUserException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String duplicatedUserExceptionHandler(DuplicatedUserException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String locationNotFoundHandler(LocationNotFoundException e) {
        return e.getMessage();
    }
}
