package com.chekhovska.lawyermvp.exception;

public class ResponseStatusException extends RuntimeException {
    public ResponseStatusException() {}

    public ResponseStatusException(String message) {
        super(message);
    }
}
