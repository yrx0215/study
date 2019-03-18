package com.jnshu.dreamteam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jnshu.dreamteam.mapper.HotRecommendMapper;
import com.jnshu.dreamteam.pojo.HotRecommend;
import com.jnshu.dreamteam.service.BaseService;
import com.jnshu.dreamteam.service.HotRecommendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author draper_hxy
 */
@Service
public class HotRecommendServiceImpl extends BaseService implements HotRecommendService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    private HotRecommendMapper hotRecommendMapper;

    @Override
    public HotRecommend select(Long id) {
        LOGGER.trace("select hotRc, id = {}", id);
        return hotRecommendMapper.selectById(id);
    }

    @Override
    public Boolean insert(HotRecommend hotRc) {
        LOGGER.trace("insert hotRc, id = {}", hotRc.getId());
        Long date = System.currentTimeMillis();
        hotRc.setCreateAt(date);
        hotRc.setUpdateAt(date);
        return getResult(hotRecommendMapper.insert(hotRc));
    }

    @Override
    public Boolean update(HotRecommend hotRc) {
        LOGGER.trace("update hotRc, id = {}", hotRc.getId());
        UpdateWrapper<HotRecommend> wrapper = new UpdateWrapper<>();
        wrapper.lambda()
                .set(HotRecommend::getUpdateAt, System.currentTimeMillis())
                .set(HotRecommend::getGrade, hotRc.getGrade())
                .set(HotRecommend::getSubjectId, hotRc.getSubjectId())
                .set(HotRecommend::getCourseId, hotRc.getCourseId())
                .set(HotRecommend::getCoverImgUrl, hotRc.getCoverImgUrl())
                .eq(HotRecommend::getId, hotRc.getId());
        return getResult(hotRecommendMapper.update(hotRc, wrapper));
    }

    @Override
    public Boolean delete(Long id) {
        LOGGER.trace("delete hotRc, id = {}", id);
        QueryWrapper<HotRecommend> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(HotRecommend::getId, id);
        return getResult(hotRecommendMapper.delete(wrapper));
    }

}
