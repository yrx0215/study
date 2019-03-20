package com.jnshu.dreamteam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Help;

import java.util.Map;

/**
 * @author draper_hxy
 */
public interface HelpService {

    IPage<Help> selectPages(Map<String,Object> params);

    Help select(Long id);

    Boolean insert(Help help);

    Boolean update(Help help);

    Boolean delete(Long id);

}
