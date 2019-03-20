package com.jnshu.dreamteam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 任务中的步骤实体类和相关属性
 * @author yrx
 */
@Data
@Component
public class MissionContent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 所属任务的id
     */
    @TableField("mission_id")
    private Long missionId;

    /**
     * 任务中的步骤
     */
    @TableField("content_step")
    private Integer contentStep;

    /**
     * 任务中的步骤类型(图片, 文字, 视频, 音频等)
     */
    @TableField("content_type")
    private Integer contentType;

    /**
     * 相关任务的具体数据
     */
    @TableField("content")
    private String content;

    @TableField("create_at")
    private Long createAt;

    @TableField("update_at")
    private Long updateAt;




}
