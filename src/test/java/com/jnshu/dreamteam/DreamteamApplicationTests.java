package com.jnshu.dreamteam;

import com.jnshu.dreamteam.mapper.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DreamteamApplicationTests {

    @Resource
    private StudentMapper studentMapper;

    @Test
    public void contextLoads() {
        System.out.println(studentMapper.selectById(1));

    }

}
