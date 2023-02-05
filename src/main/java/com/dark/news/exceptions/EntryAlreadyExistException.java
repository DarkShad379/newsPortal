package com.dark.news.exceptions;

public class EntryAlreadyExistException extends RuntimeException {
    public EntryAlreadyExistException(String message) {
        super(message);
    }
}
