package com.jnshu.dreamteam.controller;

import com.jnshu.dreamteam.pojo.Response;
import com.jnshu.dreamteam.pojo.User;
import com.jnshu.dreamteam.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 添加后台账号，目前还没做权限
     * @param user
     * @return
     */
    @PostMapping("/a/u/user")
    public Response registerUserAccount(@RequestBody User user){
        Long id = userService.insertUserOne(user);
        if(id != null){
            return Response.ok();
        }
        return Response.error();
    }
}
