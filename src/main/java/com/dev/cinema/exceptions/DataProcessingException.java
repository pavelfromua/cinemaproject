package com.dev.cinema.exceptions;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String s, Exception e) {
        super(s, e);
    }
}
