package com.aimdb.query.model;

import com.aimdb.enums.CriteriaTypeEnum;

/**
 * Created by bbking on 17-2-1.
 */
public class Criteria {
    private CriteriaTypeEnum type;
    private String key;
    private Object value;

    public Criteria(CriteriaTypeEnum type, String key, Object value) {
        this.key = key;
        this.value = value;
        this.type = type;
    }

    public CriteriaTypeEnum getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}
