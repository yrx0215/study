package com.jnshu.dreamteam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 资料模块
 * @author yrx
 */
@Component
@Data
public class Datum implements Serializable{

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 所属课程id
     */
    @TableField("course_id")
    private Long courseId;

    private Long subjectId;

    private Long lessonId;

    private String subjectName;

    private String courseName;

    private String lessonName;

    /**
     * 资料价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 资料地址
     */
    @TableField("datum")
    private String datum;

    /**
     * 创建时间
     */
    @TableField("create_at")
    private Long createAt;

    /**
     * 更新时间
     */
    @TableField("update_at")
    private Long updateAt;
}
