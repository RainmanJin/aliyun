package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 打点页面事件关联表
 * </p>
 *
 * @author ygma123
 * @since 2018-04-18
 */
@TableName("ser_bhv_page_event")
public class BhvPageEvent extends Model<BhvPageEvent> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;
    /**
     * 页面ID
     */
    @TableField("page_id")
    private Integer pageId;
    /**
     * 事件ID
     */
    @TableField("event_id")
    private Integer eventId;
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

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
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
        return "BhvPageEvent{" +
        "id=" + id +
        ", pageId=" + pageId +
        ", eventId=" + eventId +
        ", label=" + label +
        ", discribe=" + discribe +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
