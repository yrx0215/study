package com.jnshu.dreamteam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.config.exception.ServiceDaoException;
import com.jnshu.dreamteam.config.exception.ValidatedParamsOnlyException;
import com.jnshu.dreamteam.pojo.*;

import java.util.List;
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

    /**
     *
     * 多条件动态+分页查询，
     * @param iPage
     * @param nickName 昵称
     * @param account 账号
     * @param grade 年级
     * @param state 账号状态
     * @param starMin 学习星最小值
     * @param starMax 学习星最大值
     * @param studyLessonMin 学习课时最小值
     * @param studyLessonMax 学习课时最大值
     * @return
     */
    IPage<List<Student>> selectByMultiple(IPage iPage
                                  ,String nickName
                                  ,String account
                                  ,String grade
                                  ,Integer state
                                  ,Long starMin
                                  ,Long starMax
                                  ,Integer studyLessonMin
                                  ,Integer studyLessonMax) throws ServiceDaoException;

    /**
     * 分页查询，依据用户ID查询其收藏的课程列表
     * @param iPage
     * @param studentId
     * @return
     * @throws ServiceDaoException
     */
    IPage<List<Course>> selectByStudentId(IPage iPage,Long studentId) throws ServiceDaoException;

    /**
     * 分页查询，依据用户ID查询其收藏的课时列表
     * @param iPage
     * @param studentId
     * @return
     */
    IPage<List<Lesson>> selectLessonByStudentId(IPage iPage,Long studentId);

    /**
     * 分页查询，依据用户ID查询其购买的课时资料
     * @param iPage
     * @param studentId
     * @return
     */
    IPage<List<Lesson>> selectDatumByStudentId(IPage iPage,Long studentId);

    /**
     * 分页查询，依据 用户ID查询其购买的课程
     * @param iPage
     * @param studentId
     * @return
     */
    IPage<List<Lesson>> selectBuyLessonByStudentId(IPage iPage,Long studentId);

    /**
     * 更新前台用户个人信息
     * @param student
     * @throws ServiceDaoException
     */
    void updateHomeStudentById(Student student) throws ServiceDaoException;

    /**
     * 根据学生id查询学生签到状态
     * @param id 学生id
     * @return 返回值为学生的签到信息
     */
    StudentCheck selectStudentCheckById(Long id);

    /**
     * 插入签到信息
     * @param studentCheck 签到信息
     * @return 返回值为新增签到信息的id
     */
    Long insertStudentCheck(StudentCheck studentCheck);

    /**
     * 更新签到信息
     * @param studentCheck 签到信息
     * @return 返回值为true 更新成功
     */
    Boolean updateStudentCheck(StudentCheck studentCheck);

    Student getStduent(Long Id);
}
