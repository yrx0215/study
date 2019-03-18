package com.jnshu.dreamteam.controller;


import com.auth0.jwt.interfaces.Claim;
import com.jnshu.dreamteam.config.exception.ServiceDaoException;
import com.jnshu.dreamteam.pojo.ParentModule;
import com.jnshu.dreamteam.pojo.Response;
import com.jnshu.dreamteam.service.ModuleService;
import com.jnshu.dreamteam.service.ParentModuleService;
import com.jnshu.dreamteam.utils.JwtUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ModuleController {

    @Resource
    private ParentModuleService parentModuleService;
    @Resource
    private ModuleService moduleService;

    /**
     * 查询所有父模块及其子模块
     * @return
     */
    @RequiresPermissions("角色管理")
    @GetMapping("/a/u/parentAllModule")
    public Response<List<ParentModule>> selectModuleAll(){
        List<ParentModule> parentModuleList = parentModuleService.selectModuleAll();
        return new Response<>(200,"查询成功",parentModuleList);
    }

    /**
     * 根据Token中角色名，查询可显示的模块列表
     * @param request
     * @return
     */
    @RequiresAuthentication
    @GetMapping("/a/u/moduleList")
    public Response<List<ParentModule>> selectModuleByToken(HttpServletRequest request) throws ServiceDaoException{
        String token = request.getHeader("Token");
        Claim claim = JwtUtil.getClaims(token,"roleName");
        String roleName = claim.asString();
        List<ParentModule> parentModuleList = moduleService.selectModuleByRoleName(roleName);
        return new Response<>(200,"查询成功",parentModuleList);
    }


}
