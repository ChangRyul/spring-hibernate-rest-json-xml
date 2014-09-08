package com.lesula.app.error;

/**
 * Created by enrico on 9/8/14.
 */
public abstract class AppException extends Exception {

    public AppException() {
        super();
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    public AppException(String message){
        super(message);
    }

    public abstract String getCode();

}
