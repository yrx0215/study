package com.jnshu.dreamteam.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;

/**
 * @author draper_hxy
 */
public abstract class QueryWrapperBaseService<T> extends BaseService {


    public void initParams(Map<String, Object> params) {
        if (params.get("size") == null) {
            params.put("size", 8);
        }

        if (params.get("page") == null) {
            params.put("page", 1);
        }
    }

    protected abstract QueryWrapper<T> buildQueryWrapper(QueryWrapper<T> queryWrapper, Map<String, Object> params);
}
