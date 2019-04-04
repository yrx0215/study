package com.jnshu.dreamteam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.News;

import java.util.Map;

/**
 * @author draper_hxy
 */
public interface NewsService {

    IPage<News> selectPages(Map<String, Object> params);

    Boolean insert(News news);

    News select(Long id);

    Boolean update(News news);

    Boolean delete(Long id);

}
