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
@TableName("log_param_value_dic")
public class LogParamValueDic extends Model<LogParamValueDic> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * param_value的值
     */
    @TableField("param_value_code")
    private String paramValueCode;
    /**
     * param_value_name的说明
     */
    @TableField("param_value_name")
    private String paramValueName;
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

    public String getParamValueCode() {
        return paramValueCode;
    }

    public void setParamValueCode(String paramValueCode) {
        this.paramValueCode = paramValueCode;
    }

    public String getParamValueName() {
        return paramValueName;
    }

    public void setParamValueName(String paramValueName) {
        this.paramValueName = paramValueName;
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
        return "LogParamValueDic{" +
        "id=" + id +
        ", paramValueCode=" + paramValueCode +
        ", paramValueName=" + paramValueName +
        ", isDelete=" + isDelete +
        "}";
    }
}
