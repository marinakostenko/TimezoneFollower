package com.codemari.timezonefollowerrest.exception;

public class DuplicatedUserException extends RuntimeException {
    public DuplicatedUserException(String searchCriteria) {
        super("User already exists " + searchCriteria);
    }
}
