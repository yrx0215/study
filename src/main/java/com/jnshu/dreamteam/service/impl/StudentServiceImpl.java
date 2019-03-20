package com.jnshu.dreamteam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jnshu.dreamteam.config.exception.ServiceDaoException;
import com.jnshu.dreamteam.config.exception.ValidatedParamsOnlyException;
import com.jnshu.dreamteam.mapper.PhoneVerificationMapper;
import com.jnshu.dreamteam.mapper.StudentMapper;
import com.jnshu.dreamteam.pojo.PhoneVerification;
import com.jnshu.dreamteam.pojo.Student;
import com.jnshu.dreamteam.service.StudentService;
import com.jnshu.dreamteam.utils.EmptyUtil;
import com.jnshu.dreamteam.utils.Md5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
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
        return System.currentTimeMillis()-phoneVerification.getCreateAt()<86400000 && verificationCode.equals(phoneVerification.getVerificationCode());
    }

    @Override
    public Long insertStudent(Student student) throws ServiceDaoException{
        student.setState(1);
        student.setStar(0L);
        studentMapper.insert(student);
        Long id = student.getId();
        if(EmptyUtil.isEmpty(id)){
            throw new ServiceDaoException("数据库异常，添加失败");
        }
        return id;
    }

    @Override
    public void updateStudentById(Student student) throws ServiceDaoException{
        if(EmptyUtil.isEmpty(studentMapper.updateById(student))){
            throw new ServiceDaoException("数据库异常，更新数据失败");
        }
    }

    @Override
    public Map<String,Long> selectByAccountOrPhone(String account,String password) throws ServiceDaoException{
        Student student = studentMapper.selectByAccountOrPhone(account);
        if(!EmptyUtil.isEmpty(student)){
            String pwd = Md5Utils.stringMD5(password,student.getCreateAt());
            if(pwd.equals(student.getPassword())){
                Map<String, Long> map = new HashMap<>();
                map.put("userId",student.getId());
                return map;
            }
        }
        throw new ServiceDaoException("账户名或密码错误");
    }
}
