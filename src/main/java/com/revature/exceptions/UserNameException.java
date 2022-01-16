package com.revature.exceptions;

public class UserNameException extends RuntimeException{
    public UserNameException(String message){
        super(message);
    }

    public UserNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNameException(Throwable cause) {
        super(cause);
    }

    public UserNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

    }
}
