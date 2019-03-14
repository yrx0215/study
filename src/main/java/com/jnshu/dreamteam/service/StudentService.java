package com.jnshu.dreamteam.service;

import com.jnshu.dreamteam.config.exception.ValidatedParamsOnlyException;
import com.jnshu.dreamteam.pojo.PhoneVerification;
import com.jnshu.dreamteam.pojo.Student;

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

}
