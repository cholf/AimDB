package com.aimdb.exception;

/**
 * Created by bbking on 17-1-18.
 */
public class SeekException extends Exception {

    public SeekException(String msg) {
        super(msg);
    }

    public SeekException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
