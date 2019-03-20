package com.jnshu.dreamteam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.mapper.OpinionMapper;
import com.jnshu.dreamteam.pojo.Message;
import com.jnshu.dreamteam.pojo.Opinion;
import com.jnshu.dreamteam.service.BaseServiceWrapper;
import com.jnshu.dreamteam.service.OpinionService;
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
public class OpinionServiceImpl extends BaseServiceWrapper<Opinion> implements OpinionService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    private OpinionMapper opinionMapper;

    @Override
    public IPage<Opinion> selectPages(Map<String, Object> params) {
        initParams(params);
        LOGGER.trace("get opinion page, page = {}, size = {}", params.get("page"), params.get("size"));
        Long currentPage = Long.valueOf(params.get("page").toString());
        Long size = Long.valueOf(params.get("size").toString());
        MyPage<Opinion> page = new MyPage<>(currentPage, size);
        QueryWrapper<Opinion> queryWrapper = buildQueryWrapper(null, params);
        return opinionMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Boolean insert(Opinion opinion) {
        LOGGER.trace("insert opinion");
        return getResult(opinionMapper.insert(opinion));
    }

    @Override
    public Opinion select(Long id) {
        LOGGER.trace("select opinion, id = {}", id);
        return opinionMapper.selectById(id);
    }

    @Override
    public Boolean delete(Long id) {
        LOGGER.trace("delete opinion, id = {}", id);
        return getResult(opinionMapper.delete(new QueryWrapper<Opinion>().lambda().eq(Opinion::getId, id)));
    }

}
