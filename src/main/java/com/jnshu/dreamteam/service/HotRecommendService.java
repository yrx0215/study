package com.jnshu.dreamteam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.HotRecommend;

import java.util.Map;

/**
 * @author draper_hxy
 */
public interface HotRecommendService {

    IPage<HotRecommend> selectPages(Map<String, Object> params);

    HotRecommend select(Long id);

    Boolean insert(HotRecommend hotRc);

    Boolean update(HotRecommend hotRc);

    Boolean delete(Long id);

}
