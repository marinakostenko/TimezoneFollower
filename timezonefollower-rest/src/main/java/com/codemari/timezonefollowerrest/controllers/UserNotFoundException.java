package com.codemari.timezonefollowerrest.controllers;

public class UserNotFoundException extends RuntimeException {
    UserNotFoundException(long id) {
        super("Could not find user " + id);
    }
}
