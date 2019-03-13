package com.jnshu.dreamteam.service.impl;

import com.jnshu.dreamteam.mapper.StudentMapper;
import com.jnshu.dreamteam.pojo.Student;
import com.jnshu.dreamteam.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;


    @Override
    public Student selectStudentById(Long id) {
        return studentMapper.selectById(id);
    }
}
