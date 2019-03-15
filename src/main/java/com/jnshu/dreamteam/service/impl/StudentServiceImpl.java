package com.jnshu.dreamteam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jnshu.dreamteam.config.exception.ValidatedParamsOnlyException;
import com.jnshu.dreamteam.mapper.PhoneVerificationMapper;
import com.jnshu.dreamteam.mapper.StudentMapper;
import com.jnshu.dreamteam.pojo.PhoneVerification;
import com.jnshu.dreamteam.pojo.Student;
import com.jnshu.dreamteam.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;
    @Resource
    private PhoneVerificationMapper phoneVerificationMapper;


    @Override
    public Student selectStudentById(Long id) {
        return studentMapper.selectById(id);
    }

    @Override
    public Boolean validatedPhoneOnly(Long phone) throws ValidatedParamsOnlyException {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",phone);
        Integer count = studentMapper.selectCount(queryWrapper);
        if(count==0){
            return true;
        }
        throw new ValidatedParamsOnlyException("该手机号已存在");
    }

    @Override
    public Boolean validatedAccountOnly(String account) throws ValidatedParamsOnlyException {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account);
        Integer count = studentMapper.selectCount(queryWrapper);
        if(count==0){
            return true;
        }
        throw new ValidatedParamsOnlyException("该账号已存在");
    }

    @Override
    public Boolean validatedNickNameOnly(String nickName) throws ValidatedParamsOnlyException {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("nick_name",nickName);
        Integer count = studentMapper.selectCount(queryWrapper);
        if(count==0){
            return true;
        }
        throw new ValidatedParamsOnlyException("该昵称已存在");
    }

    @Override
    public void phoneVerification(PhoneVerification phoneVerification) {
        phoneVerificationMapper.phoneVerification(phoneVerification);
    }

    @Override
    public Boolean selectVerification(Long phone,Integer verificationCode) {
        QueryWrapper<PhoneVerification> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        PhoneVerification phoneVerification = phoneVerificationMapper.selectOne(queryWrapper);
        phoneVerificationMapper.delete(queryWrapper);
        return System.currentTimeMillis()-phoneVerification.getCreateAt()<60000 && verificationCode.equals(phoneVerification.getVerificationCode());
    }
}
