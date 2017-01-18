package com.aimdb.exception;

/**
 * Created by bbking on 17-1-18.
 */
public class InsertException extends Exception {

    public InsertException(String msg) {
        super(msg);
    }

    public InsertException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
