package com.stylefeng.guns.modular.system.model;

import com.google.common.base.Objects;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author tgshi123
 * @since 2018-07-29
 */
public class KsUserTagManage  {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="标签id",name="tag_id",example="1")
    private String tag_id; //标签id

    @ApiModelProperty(value="分类id",name="tag_classify_id",example="七日新用户组")
    private String tag_classify_id;//分类id
    @ApiModelProperty(value="标签分类名称",name="tag_classify_name",example="针对7日新用户进行分组")
    private String tag_classify_name;//标签分类名称



    @ApiModelProperty(value="标签值",name="tag_value",example="Seven_NEW_USER")
    private String tag_value;// 标签值

    @ApiModelProperty(value="标签名称",name="tag_name",example="针对7日新用户进行分组")
    private String tag_name;//标签名称

    @ApiModelProperty(value="标签拥有人数",name="tag_user_count",example="100")

    private String tag_user_count;// 标签拥有人数

    @ApiModelProperty(value="标签分组类别 1：代表以用户id分组  2：以设备id分组 3：不限",name="tag_type",example="1")
    private String tag_type;//标签分组类别 1：代表以用户id分组  2：以设备id分组 3：不限

    @ApiModelProperty(value="标签状态 1：动态，2：静态",name="tag_status",example="1")
    private String tag_status;//标签状态  1：动态，2：静态

    @ApiModelProperty(value="开始时间",name="starttime",example="2018-07-27T18:26:19.235+0800")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String starttime;// 开始时间

    @ApiModelProperty(value="结束时间",name="endtime",example="2018-07-27T18:26:19.235+0800")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endtime;//结束时间

    @ApiModelProperty(value="创建时间",name="createtime",example="2018-07-27T18:26:19.235+0800")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createtime;//创建时间
    private String userid;//用户id

    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTag_id() {
        return tag_id;
    }

    public void setTag_id(String tag_id) {
        this.tag_id = tag_id;
    }

    public String getTag_user_count() {
        return tag_user_count;
    }

    public void setTag_user_count(String tag_user_count) {
        this.tag_user_count = tag_user_count;
    }

    public String getTag_type() {
        return tag_type;
    }
    public String getTag_value() {
        return tag_value;
    }

    public void setTag_value(String tag_value) {
        this.tag_value = tag_value;
    }

    public void setTag_type(String tag_type) {
        this.tag_type = tag_type;
    }

    public String getTag_status() {
        return tag_status;
    }

    public void setTag_status(String tag_status) {
        this.tag_status = tag_status;
    }

    public String getTag_classify_id() {
        return tag_classify_id;
    }

    public void setTag_classify_id(String tag_classify_id) {
        this.tag_classify_id = tag_classify_id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    public String getTag_classify_name() {
        return tag_classify_name;
    }

    public void setTag_classify_name(String tag_classify_name) {
        this.tag_classify_name = tag_classify_name;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("tag_id", tag_id)
                .add("tag_classify_id", tag_classify_id)
                .add("tag_classify_name", tag_classify_name)
                .add("tag_name", tag_name)
                .add("tag_value", tag_value)
                .add("tag_user_count", tag_user_count)
                .add("tag_type", tag_type)
                .add("starttime", starttime)
                .add("endtime", endtime)
                .toString();
    }

}
