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
@TableName("log_event_dic")
public class LogEventDic extends Model<LogEventDic> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 事件code
     */
    @TableField("event_code")
    private String eventCode;
    /**
     * 事件中文名
     */
    @TableField("event_name")
    private String eventName;
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

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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
        return "LogEventDic{" +
        "id=" + id +
        ", eventCode=" + eventCode +
        ", eventName=" + eventName +
        ", isDelete=" + isDelete +
        "}";
    }
}
