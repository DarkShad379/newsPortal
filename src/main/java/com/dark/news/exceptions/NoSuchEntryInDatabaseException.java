package com.dark.news.exceptions;

public class NoSuchEntryInDatabaseException extends RuntimeException {
    public NoSuchEntryInDatabaseException(String message) {
        super(message);
    }
}
