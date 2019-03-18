package com.jnshu.dreamteam.mapper;

import com.jnshu.dreamteam.pojo.Module;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jnshu.dreamteam.pojo.ParentModule;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangziping
 * @since 2019-03-14
 */
public interface ModuleMapper extends BaseMapper<Module> {

    List<Long> selectModuleIdByRoleName(String roleName);


}
