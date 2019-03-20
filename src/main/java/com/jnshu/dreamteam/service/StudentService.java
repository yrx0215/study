package com.jnshu.dreamteam.service;

import com.jnshu.dreamteam.config.exception.ServiceDaoException;
import com.jnshu.dreamteam.config.exception.ValidatedParamsOnlyException;
import com.jnshu.dreamteam.pojo.PhoneVerification;
import com.jnshu.dreamteam.pojo.Student;
import org.apache.ibatis.annotations.Param;

import javax.xml.ws.Service;
import java.util.Map;

/**
 * @author wzp
 */
public interface StudentService {

    /**
     * 依据ID查询Student
     * @param id
     * @return
     */
    Student selectStudentById(Long id);

    /**
     * 验证手机号是否已经注册，已注册则抛异常
     * @param phone
     * @return
     * @throws ValidatedParamsOnlyException
     */
    Boolean validatedPhoneOnly(Long phone) throws ValidatedParamsOnlyException;

    /**
     * 验证账号是否已经注册，已注册则抛异常
     * @param account
     * @return
     * @throws ValidatedParamsOnlyException
     */
    Boolean validatedAccountOnly(String account) throws ValidatedParamsOnlyException;

    /**
     * 验证昵称 是否已经注册，已注册则抛异常
     * @param nickName
     * @return
     * @throws ValidatedParamsOnlyException
     */
    Boolean validatedNickNameOnly(String nickName) throws ValidatedParamsOnlyException;

    /**
     * 将验证码相关信息存入数据库
     * @param phoneVerification
     */
    void phoneVerification(PhoneVerification phoneVerification);

    /**
     * 判断验证码是否正确
     * 正确返回true 错误返回false
     * @param phone
     * @param verificationCode
     * @return
     */
    Boolean selectVerification(Long phone,Integer verificationCode);

    /**
     * 添加用户，返回用户ID
     * @param student
     * @return
     */
    Long insertStudent(Student student) throws ServiceDaoException;

    /**
     * 依据ID更新用户信息
     * @param student
     * @throws ServiceDaoException
     */
    void updateStudentById(Student student) throws ServiceDaoException;

    /**
     * 前台登录服务，账号名可为用户名也可以为手机号
     * 若密码正确，则返回带有userId的map供生成Token使用
     * 若密码错误，则抛出异常
     * @param account
     * @param password
     * @return
     * @throws ServiceDaoException
     */
    Map<String,Long> selectByAccountOrPhone(String account,String password) throws ServiceDaoException;


}
