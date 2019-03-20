package com.jnshu.dreamteam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Version;

import java.util.Map;

/**
 * @author draper_hxy
 */
public interface VersionService {

    IPage<Version> selectPages(Map<String,Object> params);

    Version select(Long id);

    Boolean insert(Version version);

    Boolean delete(Long id);

}
