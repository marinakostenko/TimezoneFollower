package com.codemari.timezonefollowerrest.rest;

public class UserNotFoundException extends RuntimeException {
    UserNotFoundException(long id) {
        super("Could not find user " + id);
    }
}
