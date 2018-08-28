package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 打点参数code表
 * </p>
 *
 * @author ygma123
 * @since 2018-04-18
 */
@TableName("ser_bhv_log")
public class BhvLog extends Model<BhvLog> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;
    /**
     * 页面事件参数关联ID
     */
    @TableField("page_event_param_id")
    private String pageEventParamId;
    /**
     * 页面名称
     */
    @TableField("page_name")
    private String pageName;
    /**
     * 页面编码
     */
    @TableField("page_code")
    private String pageCode;
    /**
     * 事件名称
     */
    @TableField("event_name")
    private String eventName;
    /**
     * 事件编码
     */
    @TableField("event_code")
    private String eventCode;
    /**
     * 参数名称
     */
    @TableField("param_name")
    private String paramName;
    /**
     * 参数编码
     */
    @TableField("param_code")
    private String paramCode;
    /**
     * 参数内容
     */
    @TableField("param_value")
    private String paramValue;
    /**
     * 描述
     */
    private String discribe;
    /**
     * 标记
     */
    private String label;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPageEventParamId() {
        return pageEventParamId;
    }

    public void setPageEventParamId(String pageEventParamId) {
        this.pageEventParamId = pageEventParamId;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageCode() {
        return pageCode;
    }

    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getDiscribe() {
        return discribe;
    }

    public void setDiscribe(String discribe) {
        this.discribe = discribe;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BhvLog{" +
        "id=" + id +
        ", pageEventParamId=" + pageEventParamId +
        ", pageName=" + pageName +
        ", pageCode=" + pageCode +
        ", eventName=" + eventName +
        ", eventCode=" + eventCode +
        ", paramName=" + paramName +
        ", paramCode=" + paramCode +
        ", paramValue=" + paramValue +
        ", discribe=" + discribe +
        ", label=" + label +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
