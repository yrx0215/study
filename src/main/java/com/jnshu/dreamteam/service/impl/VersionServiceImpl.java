package com.jnshu.dreamteam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.mapper.VersionMapper;
import com.jnshu.dreamteam.pojo.Version;
import com.jnshu.dreamteam.service.BaseServiceWrapper;
import com.jnshu.dreamteam.service.VersionService;
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
public class VersionServiceImpl extends BaseServiceWrapper<Version> implements VersionService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    private VersionMapper versionMapper;

    @Override
    public IPage<Version> selectPages(Map<String, Object> params) {
        initParams(params);
        LOGGER.trace("get version page, page = {}, size = {}", params.get("page"), params.get("size"));
        Long currentPage = Long.valueOf(params.get("page").toString());
        Long size = Long.valueOf(params.get("size").toString());
        MyPage<Version> page = new MyPage<>(currentPage, size);
        return versionMapper.selectPage(page, buildQueryWrapper(null, params));
    }

    @Override
    public Version select(Long id) {
        LOGGER.trace("select version, id = {}", id);
        return versionMapper.selectById(id);
    }

    @Override
    public Boolean insert(Version version) {
        LOGGER.trace("insert version");
        return getResult(versionMapper.insert(version));
    }

    @Override
    public Boolean delete(Long id) {
        LOGGER.trace("delete version, id = {}", id);
        return getResult(versionMapper.delete(new QueryWrapper<Version>().lambda().eq(Version::getId, id)));
    }

}
