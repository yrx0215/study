package com.jnshu.dreamteam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Response;
import com.jnshu.dreamteam.pojo.Role;
import com.jnshu.dreamteam.pojo.StatusCode;
import com.jnshu.dreamteam.utils.MyPage;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {

    /**
     * 分页查询角色列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/a/u/role")
    public Response<IPage> selectRoleAll(@RequestParam(value = "page",required = false) Integer page
                                 , @RequestParam(value = "size",required = false) Integer size){
        Role role = new Role();
        role.setId(1L);
        role.setRole("这里是角色名");
        IPage page1 = new MyPage();
        return new Response<>(StatusCode.SUCCESS,page1);
    }

    /**
     * 根据角色ID删除角色
     * @param id
     * @return
     */
    @DeleteMapping("/a/u/role/{id}")
    public Response deleteRoleById(@PathVariable("id") Long id){
        return Response.ok();
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @PostMapping("/a/u/role")
    public Response insertRole(@RequestBody Role role){
        return Response.ok();
    }

    /**
     * 更新角色信息
     * @param role 角色
     * @return
     */
    @PutMapping("/a/u/role")
    public Response updateRoleBy(@RequestBody Role role){
        return Response.ok();
    }


}
