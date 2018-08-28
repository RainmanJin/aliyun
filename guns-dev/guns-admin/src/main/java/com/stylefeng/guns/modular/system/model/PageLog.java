package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 打点原因表
 * </p>
 *
 * @author ygma123
 * @since 2018-04-18
 */
public class PageLog extends Model<PageLog> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;
    /**
     * 设备ID
     */
    private String userId;
    /**
     * pageCode
     */
    private String page;
    /**
     * eventCode
     */
    private String event;
    /**
     * param
     */
    private String param;

    /**
     * 来源
     */
    private String source;

    /**
     * 平台
     */
    private String project;

    /**
     * 入库时间
     */
    private Date createTime;
    /**
     * 触发时间
     */
    private Date eventTime;


    public Integer getId() {
        return id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
