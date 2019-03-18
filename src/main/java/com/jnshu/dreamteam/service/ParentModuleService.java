package com.jnshu.dreamteam.service;

import com.jnshu.dreamteam.pojo.ParentModule;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangziping
 * @since 2019-03-14
 */
public interface ParentModuleService{

    /**
     * 查询所有模块及其子模块
     * @return
     */
    List<ParentModule> selectModuleAll();

}
