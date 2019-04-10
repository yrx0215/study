package com.jnshu.dreamteam.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author yrx
 * 学生和课程关系表
 */
@Data
@Component
public class StudentCourse {
    @TableField("student_id")
    private Long  studentId;

    @TableField("course_id")
    private Long courseId;

    /**
     * 当前用户所对应课程的状态 , 开始学习, 继续学习
     */
    @TableField("status")
    private Integer status;

    /**
     * 用户是否收藏
     */
    @TableField("collection")
    private Integer collection;



}
