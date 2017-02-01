package com.aimdb.exception;

/**
 * Created by bbking on 17-1-18.
 */
public class DataAccessException extends Exception {

    public DataAccessException(String msg) {
        super(msg);
    }

    public DataAccessException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
