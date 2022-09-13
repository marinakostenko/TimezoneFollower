package com.codemari.timezonefollowerrest.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String identifier) {
        super("Could not find user " + identifier);
    }
}
