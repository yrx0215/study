package com.jnshu.dreamteam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.mapper.NewsMapper;
import com.jnshu.dreamteam.pojo.News;
import com.jnshu.dreamteam.service.BaseServiceWrapper;
import com.jnshu.dreamteam.service.NewsService;
import com.jnshu.dreamteam.utils.MyPage;
import org.apache.commons.beanutils.BeanMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author draper_hxy
 */
@Service
public class NewsServiceImpl extends BaseServiceWrapper<News> implements NewsService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    private NewsMapper newsMapper;

    @Override
    public IPage<News> selectPages(Map<String, Object> params) {
        initParams(params);
        LOGGER.trace("get news page, page = {}, size = {}", params.get("page"), params.get("size"));
        Long currentPage = Long.valueOf(params.get("page").toString());
        Long size = Long.valueOf(params.get("size").toString());
        MyPage<News> page = new MyPage<>(currentPage, size);
        QueryWrapper<News> queryWrapper = buildQueryWrapper(null, params);
        return newsMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Boolean insert(News news) {
        LOGGER.trace("news insert");
        Long dateTime = System.currentTimeMillis();
        news.setCreateAt(dateTime);
        news.setUpdateAt(dateTime);
        return getResult(newsMapper.insert(news));
    }

    @Override
    public News select(Long id) {
        LOGGER.trace("select news, id = {}", id);
        return newsMapper.selectById(id);
    }

    @Override
    public Boolean update(News news) {
        LOGGER.trace("update news, id = {}", news.getId());
        Map params = new BeanMap(news);
        UpdateWrapper<News> updateWrapper = buildUpdateWrapper(null, params);
        return getResult(newsMapper.update(news, updateWrapper));
    }

    @Override
    public Boolean delete(Long id) {
        LOGGER.trace("delete news, id = {}", id);
        LambdaQueryWrapper<News> deleteWrapper = new QueryWrapper<News>().lambda().eq(News::getId, id);
        return getResult(newsMapper.delete(deleteWrapper));
    }

    @Override
    protected UpdateWrapper<News> buildUpdateWrapper(UpdateWrapper<News> updateWrapper, Map<String, Object> params) {
        updateWrapper = super.buildUpdateWrapper(updateWrapper, null);
        LambdaUpdateWrapper<News> lambdaUpdateWrapper = updateWrapper.lambda();

        lambdaUpdateWrapper.set(News::getUpdateAt, System.currentTimeMillis());

        if (params.get("title") != null)
            lambdaUpdateWrapper.set(News::getTitle, params.get("title"));
        if (params.get("type") != null)
            lambdaUpdateWrapper.set(News::getType, params.get("type"));
        if (params.get("coverImg") != null)
            lambdaUpdateWrapper.set(News::getCoverImg, params.get("coverImg"));
        if (params.get("digest") != null)
            lambdaUpdateWrapper.set(News::getDigest, params.get("digest"));
        if (params.get("content") != null)
            lambdaUpdateWrapper.set(News::getContent, params.get("content"));

        lambdaUpdateWrapper.eq(News::getId, params.get("id"));
        return updateWrapper;
    }

    @Override
    protected QueryWrapper<News> buildQueryWrapper(QueryWrapper<News> queryWrapper, Map<String, Object> params) {
        queryWrapper = super.buildQueryWrapper(queryWrapper, params);
        if (params.containsKey("like"))
            queryWrapper.lambda().like(News::getTitle, params.get("like").toString());
        if (params.containsKey("type"))
            queryWrapper.lambda().eq(News::getType, params.get("type").toString());
        return queryWrapper;
    }

}
