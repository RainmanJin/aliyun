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
 * @since 2018-07-06
 */
@TableName("log_param_key_dic")
public class LogParamKeyDic extends Model<LogParamKeyDic> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * param_key值
     */
    @TableField("param_key_code")
    private String paramKeyCode;
    /**
     * param_key名字
     */
    @TableField("param_key_name")
    private String paramKeyName;
    /**
     * 是否删除
     */
    @TableField("is_delete")
    private String isDelete;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParamKeyCode() {
        return paramKeyCode;
    }

    public void setParamKeyCode(String paramKeyCode) {
        this.paramKeyCode = paramKeyCode;
    }

    public String getParamKeyName() {
        return paramKeyName;
    }

    public void setParamKeyName(String paramKeyName) {
        this.paramKeyName = paramKeyName;
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
        return "LogParamKeyDic{" +
        "id=" + id +
        ", paramKeyCode=" + paramKeyCode +
        ", paramKeyName=" + paramKeyName +
        ", isDelete=" + isDelete +
        "}";
    }
}
