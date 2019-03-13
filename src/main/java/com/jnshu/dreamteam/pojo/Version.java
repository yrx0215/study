package com.jnshu.dreamteam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 版本
 *
 * @author draper_hxy
 */
@Data
public class Version {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("create_at")
    private Long createAt;

    @TableField("update_at")
    private Long updateAt;

    // 版本名称
    @TableField("name")
    private String name;

    // 版本号
    @TableField("version")
    private String version;

    @TableField("url")
    private String url;

    // 版本信息
    @TableField("information")
    private String information;

}
