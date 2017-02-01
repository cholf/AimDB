package com.aimdb.enums;

/**
 * Created by bbking on 17-2-1.
 */
public enum CriteriaTypeEnum {

    LT(1, "", "小于"),
    GT(1, "", "大于"),
    EQ(1, "", "等于"),
    LTE(1, "", "小于等于"),
    GTE(1, "", "大于等于");

    CriteriaTypeEnum(int num, String name, String desc) {
        this.num = num;
        this.name = name;
        this.desc = desc;
    }

    private int num;
    private String name;
    private String desc;

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
