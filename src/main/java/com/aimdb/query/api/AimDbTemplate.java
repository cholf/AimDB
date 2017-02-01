package com.aimdb.query.api;

import com.aimdb.container.common.ParameterValuesMap;
import com.aimdb.exception.DataAccessException;
import com.aimdb.query.AimQuery;

import java.util.List;

/**
 * Created by bbking on 17-1-31.
 * AimDB数据库对外curd操作API
 */
public interface AimDbTemplate {

    /**
     * 获取一个对象数据
     * @param obj              对象
     * @param aq            查询组合
     * @param <T>
     * @return
     * @throws DataAccessException
     */
    public <T> T selectOne(Object obj , AimQuery aq) throws DataAccessException;

    /**
     * 获取对象数据列表
     * @param c        对象
     * @param aq            查询组合
     * @param <T>
     * @return
     * @throws DataAccessException
     */
    public <T> List<T> selectForLists(Class<T> c, AimQuery aq) throws DataAccessException;

    /**
     * 对象插入
     * @param o
     * @return
     * @throws DataAccessException
     */
    public  boolean insert(Object o) throws DataAccessException;
}
