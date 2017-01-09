package com.aimdb.core;

import com.aimdb.common.DBUtils;
import java.lang.reflect.Field;

/**
 * Created by bbking on 16-12-26.
 */
public class BaseTemplate {

    public void insert(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getType().getName().equalsIgnoreCase("java.lang.String")) {
                fields[i].setAccessible(true);
                try {
                    DBUtils.getRandomAccessFile().write((fields[i].get(object)).toString().getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        DBUtils.closeRandomAccessFile();
    }

    public void update() {

    }

    public void select() {

    }

    public void selectOne(Object object,int size) {
        byte [] buffer = new byte[size];
        Field[] fields = object.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            try {
                DBUtils.getRandomAccessFile().read(buffer);
                fields[i].set(object,new String(buffer).trim());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(object.toString());
    }
}
