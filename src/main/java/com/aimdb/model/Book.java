package com.aimdb.model;

import com.aimdb.anotation.AimField;

/**
 * Created by bbking on 17-1-17.
 */
public class Book {

    @AimField(length =15)
    private  String no;

    public String getNo() {
        return no;
    }

    public Book setNo(String no) {
        this.no = no;
        return this;
    }
}
