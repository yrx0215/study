package com.jnshu.dreamteam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;


/**
 * subject 实体类
 * @author Administrator
 */
@Data
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 学科姓名 1-3对应 语文 数学 英语
     */
    @TableField("subject_name")
    private Integer subjectName;

    /**
     * 科目上下架状态 0:下架 1:上架
     */
    @TableField("subject_status")
    private Integer subjectStatus;

    /**
     * 年级 : 1-6 对应 年级1-6
     */
    @TableField("grade")
    private Integer grade;

    /**
     * 科目创建时间
     */
    @TableField("create_at")
    private Long createAt;

    /**
     * 科目修改时间
     */
    @TableField("update_at")
    private Long updateAt;




}
