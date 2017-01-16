package com.aimdb.common;

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
    public static  String interceptStr(String input,int size) {
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
