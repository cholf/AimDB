package com.aimdb.common;

import com.aimdb.anotation.AimField;
import com.aimdb.enums.BaseTypeEnum;
import com.aimdb.exception.AimFieldException;
import com.aimdb.exception.SeekException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bbking on 16-12-26.
 */
public class CommnUtils {

    public static  final  String LENGTH="length";
    public static  final  String STRING="str";
    /**
     * 字符截取或者填充空格
     * @param input   输入字符
     * @param f       反射获取的field
     * @return
     */
    public static   Map<String,Object>  interceptStr(String input, Field f)  throws AimFieldException {
        Map<String,Object> map = new HashMap<String,Object>();
        String result = null;
        int length =0;
        AimField aimField = f.getAnnotation(AimField.class);
        if(aimField != null){
            length = aimField.length();
            if (input.getBytes().length > length) {
                byte[] newByte = new byte[length];
                for (int i = 0; i < length; i++) {
                    newByte[i]=input.getBytes()[i];
                }
                result = new String(newByte);
            } else {
                while (input.getBytes().length < length) {
                    input += "\u0000";//空格 8b
                }
                result = input;
            }
        }else {
            throw  new AimFieldException("CommnUtils-interceptStr-AimFieldException");
        }
        map.put(LENGTH,length);
        map.put(STRING,result);
        return map;
    }

    /**
     * 反射根据类型获取对应enum
     * @param name
     * @return
     */
    public static BaseTypeEnum getBasicTypeEnum(String name) {
        BaseTypeEnum[] baseTypeEnums = BaseTypeEnum.values();
        for (int i = 0; i < baseTypeEnums.length; i++) {
            BaseTypeEnum b = baseTypeEnums[i];
            if (b.getFullName().equalsIgnoreCase(name) || b.getBaseName().equalsIgnoreCase(name)) {
                return b;
            }
        }
        return null;
    }
}
