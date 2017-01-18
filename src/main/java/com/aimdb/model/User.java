package com.aimdb.model;

import com.aimdb.anotation.Field;
import com.aimdb.common.CommnUtils;

import java.lang.annotation.Annotation;

/**
 * Created by bbking on 16-12-17.
 */
public class User {

    @Field(size=17)
    private String id;

    @Field(size=25)
    private String name;

    private String phoneNum;

    public User() {

    }

    public User(String id, String name, String phoneNum) {
        this.id = id;
        this.name = name;
        this.phoneNum = phoneNum;
    }

    //偏移对象构造
    public User(String id, String name, String phoneNum, int size) {

        this.id = CommnUtils.interceptStr(this,id,size);
        this.name = CommnUtils.interceptStr(this,name,size);
        this.phoneNum = CommnUtils.interceptStr(this,phoneNum,size);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
