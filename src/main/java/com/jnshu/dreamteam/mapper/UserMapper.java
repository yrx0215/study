package com.jnshu.dreamteam.mapper;

import com.jnshu.dreamteam.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wzp
 * @since 2019-03-10
 */
public interface UserMapper extends BaseMapper<User> {


    /**
     * 多对多关系，添加后台用户及其拥有的角色，维护中间表
     * @param UserId
     * @param RoleIds
     */
    void insertUserRole(@Param("userId") Long UserId, @Param("roleIds") List<Long> RoleIds);

}
