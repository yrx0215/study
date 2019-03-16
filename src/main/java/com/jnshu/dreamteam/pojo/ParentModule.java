package com.jnshu.dreamteam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

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
public class ParentModule implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "parent_module_id", type = IdType.AUTO)
    private Long id;

    /**
     * 父模块名称
     */
    @TableField("parent_module_name")
    private String parentModuleName;


    /**
     * 拥有的子模块
     * 不作为数据库字段使用
     */
    @TableField(exist = false)
    private List<Module> moduleList;


}
