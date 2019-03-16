package com.jnshu.dreamteam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.HeadPortraitBackground;

import java.util.Map;

/**
 * @author draper_hxy
 */
public interface HeadPortraitBackgroundService {

    IPage<HeadPortraitBackground> selectPages(Map<String, Object> params);

    Boolean insert(HeadPortraitBackground head);

    Boolean delete(Long id);

    HeadPortraitBackground selectObject(Long id);

    Boolean update(HeadPortraitBackground headBg);

}
