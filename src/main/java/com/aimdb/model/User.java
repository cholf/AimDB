package com.aimdb.model;

import com.aimdb.common.CommnUtils;

/**
 * Created by bbking on 16-12-17.
 */
public class User {
    private String id;
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
        this.id = CommnUtils.interceptStr(id,size);
        this.name = CommnUtils.interceptStr(name,size);
        this.phoneNum = CommnUtils.interceptStr(phoneNum,size);
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
