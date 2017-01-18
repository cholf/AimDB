package com.aimdb.common;

import com.aimdb.anotation.Field;

import java.util.Arrays;

/**
 * Created by bbking on 16-12-26.
 */
public class CommnUtils {

    /**
     * 字符截取或者填充空格
     * @param input   输入字符
     * @param size    截取或者填充后的大小
     * @return
     */
    public static  String interceptStr(Object obj,String input,int size)  {
        try {
            java.lang.reflect.Field dd =obj.getClass().getDeclaredField("id");
            System.out.println(dd);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        java.lang.reflect.Field[] fields = obj.getClass().getDeclaredFields();
        for (java.lang.reflect.Field f :fields){
            Field field = f.getAnnotation(Field.class);
            if(field != null){
                int length = field.size();
                System.out.println(length);
            }

        }

        String result = null;
        if (input.getBytes().length > size) {
            byte[] newByte = new byte[size];
            for (int i = 0; i < size; i++) {
                newByte[i]=input.getBytes()[i];
            }
            result = new String(newByte);
        } else {
            while (input.getBytes().length < size) {
                input += "\u0000";//空格 8b
            }
            result = input;
        }
        return result;
    }


}
