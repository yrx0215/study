package com.jnshu.dreamteam.service;

import com.jnshu.dreamteam.pojo.Student;

public interface StudentService {

    /**
     * 依据ID查询Student
     * @param id
     * @return
     */
    Student selectStudentById(Long id);

}
