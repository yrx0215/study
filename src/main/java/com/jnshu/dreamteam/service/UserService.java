package com.jnshu.dreamteam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.config.exception.ValidatedParamsOnlyException;
import com.jnshu.dreamteam.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @author wzp
 */
public interface UserService {


    /**
     * 新建后台用户账号
     * @param user 后台用户对象
     * @return 添加的用户ID
     */
    Long insertUserOne(User user);

    /**
     * 更新后台账号信息，包括修改其角色
     * @param user
     */
    Integer updateUserOne(User user);


    /**
     * 根据ID删除一个账号
     * @param id 角色ID
     * @return
     */
    Integer deleteUserById(Long id);

    /**
     * page 当前页数      传入null则默认1
     * size 每页显示的数量 传入null则默认10
     * 多条件动态查询 使用mybatis plus分页插件
     * 若传入account值，则依据account查询，若传入roleName值，则根据角色名查询，若同时传入也按照account查询
     * 不传则显示全部
     */
    IPage<User> selectAll(Integer page, Integer size, String roleName, String account);

    /**
     * 判断用户密码是否正确，若正确则返回一个封装了用户ID，角色名称的Map，反之返回一个空Map
     * @param account 账号
     * @param password 密码
     * @return
     */
    Map<String,Object> validatePassword(String account, String password);

    /**
     * 验证账号是否已经注册，已注册则抛异常
     * @param account
     * @return
     * @throws ValidatedParamsOnlyException
     */
    Boolean validatedAccountOnly(String account) throws ValidatedParamsOnlyException;

    User selectUserById(Long id);
}
