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

    @TableField("course_id")
    private Long courseId;

    @TableField("price")
    private BigDecimal price;

    @TableField("datum")
    private String datum;

    @TableField("create_at")
    private Long createAt;

    @TableField("update_at")
    private Long updateAt;
}
