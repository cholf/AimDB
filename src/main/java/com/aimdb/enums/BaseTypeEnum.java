package com.aimdb.enums;

/**
 * Created by bbking on 17-1-19.
 */
public enum BaseTypeEnum {
    BYTE(1, "byte", "java.lang.Byte",  Byte.SIZE,  ""),
    SHORT(2, "short", "java.lang.Short", Short.SIZE, ""),
    INT(3, "int", "", Integer.SIZE, ""),
    LONG(4, "long", "", Long.SIZE, ""),
    FLOAT(5, "float", "", Float.SIZE, ""),
    DOUBLE(6, "double", "", Double.SIZE, ""),
    BOOLEAN(7, "boolean", "", 8, ""),
    CHAR(8, "char", "", Character.SIZE, "");

    BaseTypeEnum(int num, String baseName, String fullName, int bytes, String desc) {
        this.num = num;
        this.fullName = fullName;
        this.desc = desc;
        this.baseName = baseName;
    }

    private int num;
    private String fullName;
    private String baseName;
    private String desc;
    private int bytes;

    public int getBytes() {
        return bytes;
    }
    public String getBaseName() {
        return baseName;
    }

    public int getNum() {
        return num;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDesc() {
        return desc;
    }
}
