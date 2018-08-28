package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author tgshi123
 * @since 2018-07-09
 */
@TableName("log_details")
public class LogDetails extends Model<LogDetails> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * page字典id
     */
    @TableField("page_num")
    private String pageNum;
    /**
     * 时间字典id
     */
    @TableField("event_num")
    private String eventNum;
    /**
     * 打点种类1：版本打点 2：活动打点
     */
    private String type;
    /**
     * 打点版本
     */
    @TableField("version_id")
    private String versionId;
    /**
     * 打点适用的平台
     */
    @TableField("fit_platform_id")
    private String fitPlatformId;
    /**
     * 日志内容
     */
    @TableField("log_content")
    private String logContent;

    /**
     * 参数组
     */
    @TableField("param_group_id")
    private String paramGroupId;


    /**
     * 打点描述
     */
    @TableField("log_description")

    private String logDescription;
    /**
     * 是否删除(0:未删除 1：已删除)
     */
    @TableField("is_delete")
    private String isDelete;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    public LogDetails() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getEventNum() {
        return eventNum;
    }

    public void setEventNum(String eventNum) {
        this.eventNum = eventNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getFitPlatformId() {
        return fitPlatformId;
    }

    public void setFitPlatformId(String fitPlatformId) {
        this.fitPlatformId = fitPlatformId;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public String getParamGroupId() {
        return paramGroupId;
    }

    public void setParamGroupId(String paramGroupId) {
        this.paramGroupId = paramGroupId;
    }

    public String getLogDescription() {
        return logDescription;
    }

    public void setLogDescription(String logDescription) {
        this.logDescription = logDescription;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "LogDetails{" +
                "id=" + id +
                ", pageNum=" + pageNum +
                ", eventNum=" + eventNum +
                ", type=" + type +
                ", versionId=" + versionId +
                ", fitPlatformId=" + fitPlatformId +
                ", logContent=" + logContent +
                ", logDescription=" + logDescription +
                ", isDelete=" + isDelete +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                "}";
    }
}
