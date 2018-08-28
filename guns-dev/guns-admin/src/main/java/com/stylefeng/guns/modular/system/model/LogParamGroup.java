package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tgshi123
 * @since 2018-07-12
 */
@TableName("log_param_group")
public class LogParamGroup extends Model<LogParamGroup> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 参数组名称
     */
    @TableField("group_name")
    private String groupName;
    /**
     * 数据组描述
     */
    @TableField("group_description")
    private String groupDescription;
    /**
     * 存储包含参数对
     */
    @TableField("param_group_content")
    private String paramGroupContent;
    /**
     * 是否已删除
     */
    @TableField("is_delete")
    private String isDelete;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public String getParamGroupContent() {
        return paramGroupContent;
    }

    public void setParamGroupContent(String paramGroupContent) {
        this.paramGroupContent = paramGroupContent;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "LogParamGroup{" +
        "id=" + id +
        ", groupName=" + groupName +
        ", groupDescription=" + groupDescription +
        ", paramGroupContent=" + paramGroupContent +
        ", isDelete=" + isDelete +
        "}";
    }
}
