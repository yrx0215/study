package com.jnshu.dreamteam.service.impl;

import com.jnshu.dreamteam.mapper.StudentMapper;
import com.jnshu.dreamteam.mapper.UserMapper;
import com.jnshu.dreamteam.pojo.Role;
import com.jnshu.dreamteam.pojo.User;
import com.jnshu.dreamteam.service.UserService;
import com.jnshu.dreamteam.utils.Md5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Long insertUserOne(User user) {
        user.setCreateAt(System.currentTimeMillis());
        String passwordMd5 = Md5Utils.stringMD5(user.getPassword(),user.getCreateAt()); //密码MD5
        user.setChangeAt(user.getCreateAt());
        user.setPassword(passwordMd5);
        userMapper.insert(user);
        List<Long> roleIds = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roleIds.add(role.getId());
        }
        userMapper.insertUserRole(user.getId(),roleIds);
        return user.getId();
    }
}
