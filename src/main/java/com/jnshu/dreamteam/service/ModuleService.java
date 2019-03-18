package com.jnshu.dreamteam.service;

import com.jnshu.dreamteam.config.exception.ServiceDaoException;
import com.jnshu.dreamteam.pojo.Module;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jnshu.dreamteam.pojo.ParentModule;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangziping
 * @since 2019-03-14
 */
public interface ModuleService{


    List<ParentModule> selectModuleByRoleName(String roleName) throws ServiceDaoException;


}
