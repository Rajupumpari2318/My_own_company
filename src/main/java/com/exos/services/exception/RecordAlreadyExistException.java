package com.exos.services.exception;

public class RecordAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RecordAlreadyExistException(String msg) {
        super(msg);
    }
}