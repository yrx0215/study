package com.jnshu.dreamteam.service.impl;

import com.jnshu.dreamteam.pojo.ParentModule;
import com.jnshu.dreamteam.mapper.ParentModuleMapper;
import com.jnshu.dreamteam.service.ParentModuleService;
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
public class ParentModuleServiceImpl implements ParentModuleService {

    @Resource
    private ParentModuleMapper parentModuleMapper;

    @Override
    public List<ParentModule> selectModuleAll() {
        return parentModuleMapper.selectModuleAll();
    }
}
