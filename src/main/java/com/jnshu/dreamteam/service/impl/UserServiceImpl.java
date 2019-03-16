package com.jnshu.dreamteam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.config.exception.ValidatedParamsOnlyException;
import com.jnshu.dreamteam.mapper.UserMapper;
import com.jnshu.dreamteam.pojo.Student;
import com.jnshu.dreamteam.pojo.User;
import com.jnshu.dreamteam.service.UserService;
import com.jnshu.dreamteam.utils.EmptyUtil;
import com.jnshu.dreamteam.utils.Md5Utils;
import com.jnshu.dreamteam.utils.MyPage;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Log4j2
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
        return user.getId();
    }

    @Override
    public Integer updateUserOne(User user) {
        user.setChangeAt(System.currentTimeMillis());
        user.setPassword(Md5Utils.stringMD5(user.getPassword(),user.getCreateAt()));
        return userMapper.updateById(user);
    }

    @Override
    public Integer deleteUserById(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public IPage<User> selectAll(Integer page, Integer size, String roleName, String account) {
        log.info("根据条件查询后台账号,查询页数为{}，每页显示条数为{},角色姓名为{},账号为{}",page,size,roleName,account);
        page = page==null||page<=0?1:page;
        size = size==null||size<=0?10:size;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        IPage<User> myPage = new MyPage<>(page,size);
        if(roleName==null&&account==null){
            return userMapper.selectPage(myPage,queryWrapper);
        }else if(account!=null){
            queryWrapper.eq("account",account);
            return userMapper.selectPage(myPage,queryWrapper);
        }else {
            queryWrapper.eq("role_name",roleName);
            return userMapper.selectPage(myPage,queryWrapper);
        }
    }

    @Override
    public Map<String,Object> validatePassword(String account, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account);
        User user = userMapper.selectOne(queryWrapper);
        Map<String,Object> map = new HashMap<>();
        if(!EmptyUtil.isEmpty(user) && user.getPassword().equals(Md5Utils.stringMD5(password,user.getCreateAt()))){
            map.put("userId",user.getId());
            map.put("roleName",user.getRoleName());
        }
        return map;
    }

    @Override
    public Boolean validatedAccountOnly(String account) throws ValidatedParamsOnlyException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account);
        Integer count = userMapper.selectCount(queryWrapper);
        if(count==0){
            return true;
        }
        throw new ValidatedParamsOnlyException("该账号已存在");
    }

    @Override
    public User selectUserById(Long id) {
        return userMapper.selectById(id);
    }
}
