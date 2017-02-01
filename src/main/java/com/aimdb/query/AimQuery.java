package com.aimdb.query;

import com.aimdb.enums.CriteriaTypeEnum;
import com.aimdb.query.model.Criteria;
import com.aimdb.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bbking on 17-2-1.
 */
public class AimQuery {
    public static final String ORDER_ASC = "asc";
    public static final String ORDER_DESC = "desc";
    private final Map<String, Integer> fieldsMap = new HashMap();
    private final Map<String, Criteria> criteriaMap = new HashMap();

    public Map<String, Integer> getFieldsMap() {
        return fieldsMap;
    }

    public Map<String, Criteria> getCriteriaMap() {
        return criteriaMap;
    }

    /****************** 查询包含列 ********************/
    private AimQuery appendQueryFields(String fieldName, boolean isInclude) {
        if (!StringUtils.isBlank(fieldName)) {
            String[] fields = fieldName.split(",");
            for (String field : fields) {
                if (isInclude) {
                    fieldsMap.put(field, Integer.valueOf(1));
                } else {
                    fieldsMap.put(field, Integer.valueOf(0));
                }
            }
        }
        return this;
    }

    public AimQuery appendIncludeFields(String fieldName) {
        return appendQueryFields(fieldName, true);
    }

    public AimQuery appendExcludeFields(String fieldName) {
        return appendQueryFields(fieldName, false);
    }

    /****************** 条件查询 *********************/
    public AimQuery appendIsCriteria(String fieldName, Object value) {
        return appendCriteria(fieldName, value, CriteriaTypeEnum.EQ);
    }

    public AimQuery appendCriteria(String fieldName, Object value, CriteriaTypeEnum typeEnum) {
        criteriaMap.put(fieldName, new Criteria(typeEnum, fieldName, value));
        return this;
    }

    /****************** 排序 *********************/

}
