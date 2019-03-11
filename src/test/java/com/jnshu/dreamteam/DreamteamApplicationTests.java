package com.jnshu.dreamteam;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.jnshu.dreamteam.mapper.StudentMapper;
import com.jnshu.dreamteam.mapper.UserMapper;
import com.jnshu.dreamteam.pojo.Role;
import com.jnshu.dreamteam.pojo.Student;
import com.jnshu.dreamteam.pojo.User;
import com.jnshu.dreamteam.utils.MyPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DreamteamApplicationTests {

    @Resource
    private StudentMapper studentMapper;
    @Resource
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        System.out.println(studentMapper.selectById(1));
    }

    @Test
    public void insert(){
        Student student = new Student();
        student.setPassword("slkfjsdk");
        student.setStar(0L);
        student.setState(1);
        for (int i = 0; i < 20; i++) {
            student.setPhone((long) i);
            studentMapper.insert(student);
        }
    }

    @Test
    public void select(){

        IPage<Student> page = new MyPage<>(1,3);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        IPage<Student> page1 = studentMapper.selectPage(page,queryWrapper);
        System.out.println(page1.toString());
    }

    @Test
    public void select1(){
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        List<Student> studentList = studentMapper.selectList(queryWrapper);
        for (Student student : studentList) {
            System.out.println(student.toString());
        }
    }

    @Test
    public void insert1(){
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        userMapper.insertUserRole(1L,list);
    }

    @Test
    public void json(){
        User user = new User();
        user.setPassword("123456");
        user.setAccount("123456@qq.com");
        user.setPhone(9034892304L);
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setId(1L);
        Role role1 = new Role();
        role1.setId(2L);
        roles.add(role);
        roles.add(role1);
        user.setRoles(roles);
        Gson gson = new Gson();
        System.out.println(gson.toJson(user));
    }

}
