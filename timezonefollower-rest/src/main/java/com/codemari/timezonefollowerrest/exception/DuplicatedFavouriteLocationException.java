package com.codemari.timezonefollowerrest.exception;

public class DuplicatedFavouriteLocationException extends RuntimeException {
    public DuplicatedFavouriteLocationException(String searchCriteria) {
        super("Favourite location already added " + searchCriteria);
    }
}
