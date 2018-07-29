package com.gxh.apserver.exceptions;

public class InvalidStatusException extends Exception {
    private static final long serialVersionUID = -897667343818192794L;
    public InvalidStatusException() {
        super();
    }
    public InvalidStatusException(String message) {
        super(message);
    }
    public InvalidStatusException(Throwable e) { super(e); }
}
