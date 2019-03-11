package com.jnshu.dreamteam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author draper_hxy
 */
@Data
public class Message {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("create_at")
    private Long createAt;

    @TableField("update_at")
    private Long updateAt;

    @TableField("title")
    private String title;

    // 发送人群类型
    @TableField("send_type")
    private Integer sendType;

    // 消息类型(定时发送)
    @TableField("message_type")
    private Integer messageType;

    // 发送时间
    @TableField("send_time")
    private Long sendTime;

    // 消息内容-富文本
    @TableField("message")
    private String message;

}
