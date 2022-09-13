package com.codemari.timezonefollowerrest.exception;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(String identifier) {
        super("Could not find location " + identifier);
    }
}
