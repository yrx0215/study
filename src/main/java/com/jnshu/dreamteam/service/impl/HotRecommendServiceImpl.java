package com.jnshu.dreamteam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.mapper.HotRecommendMapper;
import com.jnshu.dreamteam.pojo.HotRecommend;
import com.jnshu.dreamteam.service.BaseServiceWrapper;
import com.jnshu.dreamteam.service.HotRecommendService;
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
public class HotRecommendServiceImpl extends BaseServiceWrapper<HotRecommend> implements HotRecommendService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    private HotRecommendMapper hotRecommendMapper;

    @Override
    public IPage<HotRecommend> selectPages(Map<String, Object> params) {
        initParams(params);
        LOGGER.trace("get hotRc page, page = {}, size = {}", params.get("page"), params.get("size"));
        Long currentPage = Long.valueOf(params.get("page").toString());
        Long size = Long.valueOf(params.get("size").toString());
        MyPage<HotRecommend> page = new MyPage<>(currentPage, size);
        return hotRecommendMapper.selectPage(page, buildQueryWrapper(null, params));
    }

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
        Map params = new BeanMap(hotRc);
        UpdateWrapper<HotRecommend> updateWrapper = buildUpdateWrapper(null, params);
        return getResult(hotRecommendMapper.update(hotRc, updateWrapper));
    }

    @Override
    public Boolean delete(Long id) {
        LOGGER.trace("delete hotRc, id = {}", id);
        QueryWrapper<HotRecommend> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(HotRecommend::getId, id);
        return getResult(hotRecommendMapper.delete(wrapper));
    }

    @Override
    protected UpdateWrapper<HotRecommend> buildUpdateWrapper(UpdateWrapper<HotRecommend> updateWrapper, Map<String, Object> params) {
        updateWrapper = super.buildUpdateWrapper(updateWrapper, params);
        LambdaUpdateWrapper<HotRecommend> lambdaUpdateWrapper = updateWrapper.lambda();

        lambdaUpdateWrapper.set(HotRecommend::getUpdateAt, System.currentTimeMillis());

        if (params.get("grade") != null)
            lambdaUpdateWrapper.set(HotRecommend::getGrade, params.get("grade"));
        if (params.get("subjectId") != null)
            lambdaUpdateWrapper.set(HotRecommend::getSubjectId, params.get("subjectId"));
        if (params.get("courseId") != null)
            lambdaUpdateWrapper.set(HotRecommend::getCourseId, params.get("courseId"));
        if (params.get("coverImgUrl") != null)
            lambdaUpdateWrapper.set(HotRecommend::getCoverImgUrl, params.get("coverImgUrl"));

        lambdaUpdateWrapper.eq(HotRecommend::getId, params.get("id"));
        return updateWrapper;
    }

}
