package com.jnshu.dreamteam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.mapper.HeadPortraitBackgroundMapper;
import com.jnshu.dreamteam.pojo.HeadPortraitBackground;
import com.jnshu.dreamteam.service.BaseService;
import com.jnshu.dreamteam.service.HeadPortraitBackgroundService;
import com.jnshu.dreamteam.utils.MyPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author draper_hxy
 */
@Service
public class HeadPortraitBackgroundServiceImpl extends BaseService implements HeadPortraitBackgroundService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    private HeadPortraitBackgroundMapper headPortraitBackgroundMapper;

    @Override
    public IPage<HeadPortraitBackground> selectPages(Map<String, Object> params) {
        LOGGER.trace("get backBg page, page = {}, size = {}", params.get("page"), params.get("size"));
        Long currentPage = Long.valueOf(params.get("page").toString());
        Long size = Long.valueOf(params.get("size").toString());
        MyPage<HeadPortraitBackground> page = new MyPage<>(currentPage, size);
        QueryWrapper<HeadPortraitBackground> queryWrapper = new QueryWrapper<>();
        return headPortraitBackgroundMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Boolean insert(HeadPortraitBackground head) {
        LOGGER.trace("insert headBg");
        Long date = System.currentTimeMillis();
        head.setCreateAt(date);
        head.setUpdateAt(date);
        return getResult(headPortraitBackgroundMapper.insert(head));
    }

    @Override
    public Boolean delete(Long id) {
        LOGGER.trace("delete headBg, id = {}", id);
        QueryWrapper<HeadPortraitBackground> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(HeadPortraitBackground::getId, id);
        return getResult(headPortraitBackgroundMapper.delete(queryWrapper));
    }

    @Override
    public HeadPortraitBackground selectObject(Long id) {
        LOGGER.trace("select headBg, id = {}", id);
        return headPortraitBackgroundMapper.selectById(id);
    }

    @Override
    public Boolean update(HeadPortraitBackground headBg) {
        LOGGER.trace("update headBg, id = {}", headBg.getId());
        UpdateWrapper<HeadPortraitBackground> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(HeadPortraitBackground::getUpdateAt, System.currentTimeMillis())
                .set(HeadPortraitBackground::getBackgroundImgUrl, headBg.getBackgroundImgUrl())
                .set(HeadPortraitBackground::getUpdateAt, System.currentTimeMillis())
                .eq(HeadPortraitBackground::getId, headBg.getId());
        return getResult(headPortraitBackgroundMapper.update(headBg, updateWrapper));
    }

}
