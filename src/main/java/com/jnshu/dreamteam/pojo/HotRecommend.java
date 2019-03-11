package com.jnshu.dreamteam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 热点推荐
 *
 * @author draper_hxy
 */
@Data
public class HotRecommend implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("create_at")
    private Long createAt;

    @TableField("update_at")
    private Long updateAt;

    @TableField("grade")
    private Integer grade;

    @TableField("subject_id")
    private Integer subject_id;

    // 课程
    @TableField("course_id")
    private Integer course_id;

    @TableField("cover_img_url")
    private String coverImgUrl;

}
