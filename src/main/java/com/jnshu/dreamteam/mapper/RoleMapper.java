package com.jnshu.dreamteam.mapper;

import com.jnshu.dreamteam.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wzp
 * @since 2019-03-10
 */
public interface RoleMapper extends BaseMapper<Role> {

    void insertRoleModule(@Param("roleId") Long roleId ,@Param("moduleIds") List<Long> moduleIds);

    Long deleteRoleModuleById(@Param("roleId") Long roleId);

    Role selectModuleByRoleId(@Param("roleId") Long roleId);

}
