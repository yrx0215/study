package com.jnshu.dreamteam.service;

import com.jnshu.dreamteam.pojo.HotRecommend;

/**
 * @author draper_hxy
 */
public interface HotRecommendService {

    HotRecommend select(Long id);

    Boolean insert(HotRecommend hotRc);

    Boolean update(HotRecommend hotRc);

    Boolean delete(Long id);

}
