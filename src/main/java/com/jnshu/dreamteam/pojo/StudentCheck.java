package com.jnshu.dreamteam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author yrx
 */
@Component
@Data
public class StudentCheck implements Serializable{

    private static final long serialVersionUID = 1L;


    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 签到学生id
     */
    @TableField("student_id")
    private Long studentId;

    /**
     * 上一次签到时间
     */
    @TableField("check_time")
    private Long checkTime;

    /**
     * 连续签到天数
     *
     */
    @TableField("check_day")
    private Integer checkDay;
}
