package com.jnshu.dreamteam.service;

/**
 * @author draper_hxy
 */
public abstract class BaseService {

    public Boolean getResult(int resultInt) {
        return resultInt == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

}
