package com.jnshu.dreamteam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Opinion;

import java.util.Map;

/**
 * @author draper_hxy
 */
public interface OpinionService {

    IPage<Opinion> selectPages(Map<String, Object> params);

    Boolean insert(Opinion opinion);

    Opinion select(Long id);

    Boolean delete(Long id);

}
