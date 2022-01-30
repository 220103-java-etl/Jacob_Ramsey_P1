package com.revature.exceptions;

public class PassWordException extends RuntimeException{
    public PassWordException(String message){
        super(message);
    }

    public PassWordException(String message, Throwable cause) {
            super(message, cause);
        }

    public PassWordException(Throwable cause) {
            super(cause);
        }

    public PassWordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);

    }
}
