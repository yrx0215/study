package com.jnshu.dreamteam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wzp
 * @since 2019-03-10
 */
@Data
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 课程名称
     */
    @TableField("course_name")
    private String courseName;

    /**
     * 课程难度等级
     */
    @TableField("course_level")
    private Integer courseLevel;

    /**
     * 课程学习总人数
     */
    @TableField("study_number")
    private Integer studyNumber;

    /**
     * 课程状态 0:下架 1:上架
     */
    @TableField("course_status")
    private Integer courseStatus;

    /**
     * 课程封面图片地址
     */
    @TableField("course_pic")
    private String coursePic;

    /**
     * 课程简介
     */
    @TableField("course_introduction")
    private String courseIntroduction;

    /**
     * 所属科目id
     */
    @TableField("subject_id")
    private Integer subjectId;

    /**
     * 课程创建时间
     */
    @TableField("create_at")
    private Long createAt;

    /**
     * 课程修改时间
     */
    @TableField("update_at")
    private Long updateAt;


}
