package com.aimdb.common;

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
        if (input.getBytes().length >= size) {
            result = input.substring(0, size);
        } else {
            while (input.getBytes().length < size) {
                input += "\u0000";//空格 8b
            }
            result = input;
        }
        return result;
    }


}
