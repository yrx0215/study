package com.jnshu.dreamteam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 资讯
 *
 * @author draper_hxy
 */
@Data
public class News {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("create_at")
    private Long createAt;

    @TableField("update_at")
    private Long updateAt;

    @TableField("title")
    private String title;

    // 类型
    @TableField("type")
    private Integer type;

    // 封面图片地址
    @TableField("cover_img")
    private String coverImg;

    // 摘要
    @TableField("digest")
    private String digest;

    // 资讯内容-富文本
    @TableField("content")
    private String content;

    // 排序
    @TableField("sort")
    private Long sort;

    // 上下架
    @TableField("state")
    private Integer state;

}
