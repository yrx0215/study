package com.jnshu.dreamteam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.config.exception.ServiceDaoException;
import com.jnshu.dreamteam.mapper.RoleMapper;
import com.jnshu.dreamteam.mapper.UserMapper;
import com.jnshu.dreamteam.pojo.Module;
import com.jnshu.dreamteam.pojo.Role;
import com.jnshu.dreamteam.pojo.User;
import com.jnshu.dreamteam.service.RoleService;
import com.jnshu.dreamteam.service.UserService;
import com.jnshu.dreamteam.utils.EmptyUtil;
import com.jnshu.dreamteam.utils.MyPage;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public Long insertRole(Role role) throws ServiceDaoException {
         QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
         queryWrapper.eq("role",role.getRole());
         if(roleMapper.selectCount(queryWrapper)>0){
             throw new ServiceDaoException("添加失败，己经有同名角色");
         }
         if(roleMapper.insert(role)>0){
             List<Long> moduleIds = new ArrayList<>();
             for (Module module : role.getModuleId()) {
                 moduleIds.add(module.getId());
             }
             log.info("角色id为"+moduleIds);
             roleMapper.insertRoleModule(role.getId(),moduleIds);
             return role.getId();
         }
         throw new ServiceDaoException("添加角色失败");
    }

    @Override
    public void deleteRole(Long roleId) throws ServiceDaoException {
        Role role = roleMapper.selectById(roleId);
        if(EmptyUtil.isEmpty(role)){
            throw new ServiceDaoException("无ID为"+role+"的角色");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name",role.getRole());
        if (userMapper.selectCount(queryWrapper)==0){
            roleMapper.deleteById(roleId);
            roleMapper.deleteRoleModuleById(roleId);
        }else {
            throw new ServiceDaoException("删除失败，请将该角色取消关联账号后，再重新删除");
        }
    }

    @Override
    public void updateRole(Role role) throws ServiceDaoException{
        if (roleMapper.updateById(role)>0){
            roleMapper.deleteRoleModuleById(role.getId());
            List<Long> moduleIds = new ArrayList<>();
            for (Module module : role.getModuleId()) {
                moduleIds.add(module.getId());
            }
            roleMapper.insertRoleModule(role.getId(),moduleIds);
        }else {
            throw new ServiceDaoException("更新角色失败");
        }
    }

    @Override
    public Role selectModuleByRoleId(Long roleId) throws ServiceDaoException {
        Role role = roleMapper.selectModuleByRoleId(roleId);
        if(role==null){
            throw new ServiceDaoException("无该角色");
        }
        return role;
    }

    @Override
    public IPage<Role> selectRoleAll(Integer page, Integer size){
        page = page==null||page<=0?1:page;
        size = size==null||size<=0?10:size;
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        IPage<Role> iPage = new MyPage<>(page,size);
        return roleMapper.selectPage(iPage,queryWrapper);
    }

    @Override
    public Boolean validatedRoleName(String roleName) throws ServiceDaoException{
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role",roleName);
        if(roleMapper.selectCount(queryWrapper) != 0){
            throw new ServiceDaoException("该角色已存在");
        }
        return true;
    }
}

