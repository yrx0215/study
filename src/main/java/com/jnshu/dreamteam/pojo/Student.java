package com.jnshu.dreamteam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author wzp
 * @since 2019-03-10
 */
@Data
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 用户名（可当做账号使用）
     */
    @TableField("student_account")
    private String studentAccount;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 年级
     */
    @TableField("grade")
    private String grade;

    /**
     * 手机号
     */
    @TableField("phone")
    private Long phone;

    /**
     * 0表示冻结，1表示活跃
     */
    @TableField("state")
    private Integer state;

    /**
     * 头像url
     */
    @TableField("picture")
    private String picture;

    /**
     * 学习星数
     */
    @TableField("star")
    private Long star;

    /**
     * 该学生学习课时数
     * 不作为数据库字段使用
     */
    @TableField(exist = false)
    private Long studyingLesson;

    /**
     * 学生收藏的课程
     * 不作为数据库字段使用
     */
    @TableField(exist = false)
    private List<Course> enshrineCourses;

    /**
     * 学生收藏的课程
     * 不作为数据库字段使用
     */
    @TableField(exist = false)
    private List<Lesson> enshrineLesson;
}
