package com.chekhovska.lawyerclient.exception;

public class NotFoundException extends RuntimeException {
    /**
     * Constructor for NotFoundException.
     *
     * @param message - giving message.
     */
    public NotFoundException(String message) {
        super(message);
    }
}
