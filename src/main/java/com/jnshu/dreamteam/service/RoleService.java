package com.jnshu.dreamteam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.jnshu.dreamteam.config.exception.ServiceDaoException;
import com.jnshu.dreamteam.pojo.Role;

public interface RoleService {

    /**
     * 添加角色及其权限
     * @param role
     * @return
     * @throws ServiceDaoException
     */
    Long insertRole(Role role) throws ServiceDaoException;

    /**
     * 删除角色及其权限
     * @param roleId
     * @throws ServiceDaoException
     */
    void deleteRole(Long roleId) throws ServiceDaoException;

    /**
     * 更新角色及其权限
     * @param role
     * @throws ServiceDaoException
     */
    void updateRole(Role role) throws ServiceDaoException;

    /**
     * 查询角色及其权限
     * @param roleId
     * @return
     * @throws ServiceDaoException
     */
    Role selectModuleByRoleId(Long roleId) throws ServiceDaoException;

    /**
     * 判断角色名是否唯一
     * @param roleName
     * @return
     */
    Boolean validatedRoleName(String roleName) throws ServiceDaoException;

    /**
     * 分页查询角色
     * @param page
     * @param size
     * @return
     * @throws ServiceDaoException
     */
    IPage<Role> selectRoleAll(Integer page, Integer size);
}