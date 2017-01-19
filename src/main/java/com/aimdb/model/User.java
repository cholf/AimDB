package com.aimdb.model;

import com.aimdb.anotation.AimField;
import com.aimdb.common.CommnUtils;

/**
 * Created by bbking on 16-12-17.
 */
public class User {

    @AimField(length = 17)
    private String id;

    @AimField(length = 25)
    private String name;

    @AimField(length = 25)
    private String phoneNum;

    private int age;

    public User() {

    }

    public User(String id, String name, String phoneNum, int age) {
        this.id = id;
        this.name = name;
        this.phoneNum = phoneNum;
        this.age = age;
    }

    // 偏移对象构造
    /*
     * public User(String id, String name, String phoneNum, int size) { this.id = CommnUtils.interceptStr(this,id,size);
     * this.name = CommnUtils.interceptStr(this,name,size); this.phoneNum = CommnUtils.interceptStr(this,phoneNum,size);
     * }
     */

    public int getAge() {
        return age;
    }

    public User setAge(int age) {
        this.age = age;
        return this;
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
