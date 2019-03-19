package com.jnshu.dreamteam.service;

import com.jnshu.dreamteam.pojo.News;

/**
 * @author draper_hxy
 */
public interface NewsService {

    Boolean insert(News news);

    News select(Long id);

    Boolean update(News news);

}
