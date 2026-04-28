package com.org.invmgm.exception;


public class InsufficientQuantityException extends RuntimeException{
    public InsufficientQuantityException (String message) {
        super(message);
    }
}
