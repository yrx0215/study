package com.jnshu.dreamteam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangziping
 * @since 2019-03-14
 */

@Data
public class Module implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 模块
     */
    @TableField("module")
    private String module;

    /**
     * 父模块ID
     */
    @TableField("parent_module_id")
    private Long parentModuleId;



}
