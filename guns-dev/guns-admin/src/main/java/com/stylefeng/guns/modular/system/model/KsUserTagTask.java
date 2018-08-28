package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng123
 * @since 2018-08-13
 */
@TableName("ks_user_tag_task")
public class KsUserTagTask {

    private static final long serialVersionUID = 1L;

    public String createtime;
    public String cron_expression;
    public String datasource_id;
    public String datasource_name;
    public String datasource_type;
    public String id;
    public String incremental;
    public String set_name;
    public String status;
    public String tag_classify_describe;
    public String tag_classify_id;
    public String tag_classify_name;
    public String tag_id;
    public String tag_name;
    public String tag_value;
    public String hour;
    public String minutes;

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getTag_classify_name() {
        return tag_classify_name;
    }

    public void setTag_classify_name(String tag_classify_name) {
        this.tag_classify_name = tag_classify_name;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public String getTag_value() {
        return tag_value;
    }

    public void setTag_value(String tag_value) {
        this.tag_value = tag_value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag_id() {
        return tag_id;
    }

    public void setTag_id(String tag_id) {
        this.tag_id = tag_id;
    }

    public String getDatasource_id() {
        return datasource_id;
    }

    public void setDatasource_id(String datasource_id) {
        this.datasource_id = datasource_id;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCron_expression() {
        return cron_expression;
    }

    public void setCron_expression(String cron_expression) {
        this.cron_expression = cron_expression;
    }

    public String getDatasource_name() {
        return datasource_name;
    }

    public void setDatasource_name(String datasource_name) {
        this.datasource_name = datasource_name;
    }

    public String getDatasource_type() {
        return datasource_type;
    }

    public void setDatasource_type(String datasource_type) {
        this.datasource_type = datasource_type;
    }

    public String getIncremental() {
        return incremental;
    }

    public void setIncremental(String incremental) {
        this.incremental = incremental;
    }

    public String getSet_name() {
        return set_name;
    }

    public void setSet_name(String set_name) {
        this.set_name = set_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTag_classify_describe() {
        return tag_classify_describe;
    }

    public void setTag_classify_describe(String tag_classify_describe) {
        this.tag_classify_describe = tag_classify_describe;
    }

    public String getTag_classify_id() {
        return tag_classify_id;
    }

    public void setTag_classify_id(String tag_classify_id) {
        this.tag_classify_id = tag_classify_id;
    }

}
