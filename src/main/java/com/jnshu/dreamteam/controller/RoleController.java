package com.jnshu.dreamteam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.config.exception.ServiceDaoException;
import com.jnshu.dreamteam.pojo.Response;
import com.jnshu.dreamteam.pojo.Role;
import com.jnshu.dreamteam.pojo.StatusCode;
import com.jnshu.dreamteam.service.RoleService;
import com.jnshu.dreamteam.utils.MyPage;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
@Log4j2
@RestController
@RequiresPermissions("角色管理")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 分页查询角色列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/a/u/role")
    public Response<IPage> selectRoleAll(@RequestParam(value = "page",required = false) Integer page
                                 , @RequestParam(value = "size",required = false) Integer size){
        page = page==null?1:page;
        size = size==null?10:size;
        IPage page1 = roleService.selectRoleAll(page,size);
        return new Response<>(StatusCode.SUCCESS,page1);
    }

    /**
     * 根据角色ID删除角色
     * @param id
     * @return
     */
    @DeleteMapping("/a/u/role/{id}")
    public Response deleteRoleById(@PathVariable("id") Long id) throws ServiceDaoException{
        roleService.deleteRole(id);
        return Response.ok();
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @PostMapping("/a/u/role")
    public Response<String> insertRole(@RequestBody Role role) throws ServiceDaoException {
        roleService.validatedRoleName(role.getRole());
        Long id = roleService.insertRole(role);
        return new Response<>(200,"添加成功","添加的角色ID为"+id);
    }

    /**
     * 更新角色信息
     * @param role 角色
     * @return
     */
    @PutMapping("/a/u/role")
    public Response updateRoleBy(@RequestBody Role role) throws ServiceDaoException{
        roleService.updateRole(role);
        return Response.ok();
    }

    /**
     * 根据角色ID查询模块权限
     * @param roleId
     * @return
     */
    @GetMapping("/a/u/roleModule")
    public Response<Role> selectModuleByRole(@RequestParam("roleId") Long roleId) throws ServiceDaoException {
        Role role = roleService.selectModuleByRoleId(roleId);
        return new Response<>(200, "查询成功", role);
    }
}
