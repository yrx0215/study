package com.jnshu.dreamteam.service.impl;

import com.jnshu.dreamteam.config.exception.ServiceDaoException;
import com.jnshu.dreamteam.mapper.ParentModuleMapper;
import com.jnshu.dreamteam.pojo.Module;
import com.jnshu.dreamteam.mapper.ModuleMapper;
import com.jnshu.dreamteam.pojo.ParentModule;
import com.jnshu.dreamteam.service.ModuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangziping
 * @since 2019-03-14
 */
@Service
public class ModuleServiceImpl implements ModuleService {

    @Resource
    private ModuleMapper moduleMapper;
    @Resource
    private ParentModuleMapper parentModuleMapper;


    @Override
    public List<ParentModule> selectModuleByRoleName(String roleName) throws ServiceDaoException {
        List<Long> moduleIds = moduleMapper.selectModuleIdByRoleName(roleName);
        List<ParentModule> parentModuleList = parentModuleMapper.selectModuleByRole(moduleIds);
        if(parentModuleList==null){
            throw new ServiceDaoException("该账号无可查看模块");
        }
        return parentModuleList;
    }
}
