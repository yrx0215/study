package com.jnshu.dreamteam.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import java.util.Map;

/**
 * @author draper_hxy
 */
public abstract class BaseServiceWrapper<T> extends QueryWrapperBaseService<T> {

    @Override
    public void initParams(Map<String, Object> params) {
        super.initParams(params);
    }

    @Override
    protected QueryWrapper<T> buildQueryWrapper(QueryWrapper<T> queryWrapper, Map<String, Object> params){
        if (queryWrapper == null){
            return new QueryWrapper<>();
        }
        return queryWrapper;
    }

    protected UpdateWrapper<T> buildUpdateWrapper(UpdateWrapper<T> updateWrapper, Map<String, Object> params) {
        if (updateWrapper == null){
            return new UpdateWrapper<>();
        }
        return updateWrapper;
    }

}
