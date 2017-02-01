package com.aimdb.container.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bbking on 17-2-1.
 */
public class ParameterValuesMap {
    private final Map<String, Object> values = new HashMap();

    public ParameterValuesMap addValue(String param, Object o) {
        this.values.put(param, o);
        return this;
    }

    public Object getValue(String param) {
        return this.values.get(param);
    }
}
