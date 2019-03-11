package com.jnshu.dreamteam.service;

import com.jnshu.dreamteam.pojo.User;

import java.util.List;

public interface UserService {


    /**
     * 新建后台用户账号
     * @param user 后台用户对象
     * @return 添加的用户ID
     */
    Long insertUserOne(User user);
}
