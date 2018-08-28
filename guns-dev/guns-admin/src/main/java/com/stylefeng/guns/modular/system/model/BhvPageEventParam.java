package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 打点页面事件参数关联表
 * </p>
 *
 * @author ygma123
 * @since 2018-04-18
 */
@TableName("ser_bhv_page_event_param")
public class BhvPageEventParam extends Model<BhvPageEventParam> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;
    /**
     * 页面事件关联表的ID
     */
    @TableField("page_event_id")
    private Integer pageEventId;
    /**
     * 参数ID
     */
    @TableField("param_id")
    private Integer paramId;
    /**
     * 标记
     */
    private String label;
    /**
     * 描述
     */
    private String discribe;
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

    public Integer getPageEventId() {
        return pageEventId;
    }

    public void setPageEventId(Integer pageEventId) {
        this.pageEventId = pageEventId;
    }

    public Integer getParamId() {
        return paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDiscribe() {
        return discribe;
    }

    public void setDiscribe(String discribe) {
        this.discribe = discribe;
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
        return "BhvPageEventParam{" +
        "id=" + id +
        ", pageEventId=" + pageEventId +
        ", paramId=" + paramId +
        ", label=" + label +
        ", discribe=" + discribe +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
