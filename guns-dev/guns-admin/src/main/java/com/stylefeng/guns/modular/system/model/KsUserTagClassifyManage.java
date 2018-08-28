package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 标签分类管理
 * </p>
 *
 * @author tgshi123
 * @since 2018-08-01
 */
@TableName("ks_user_tag_classify_manage")
public class KsUserTagClassifyManage {

    private static final long serialVersionUID = 1L;

    /**
     * 标签分类管理
     */
    private String tag_classify_id;
    /**
     * 标签分类名称
     */
    private String tag_classify_name;
    /**
     * 标签分类描述
     */
    private String tag_classify_describe;
    /**
     * 创建时间
     */
    private String createtime;


    public String getTag_classify_id() {
        return tag_classify_id;
    }

    public void setTag_classify_id(String tag_classify_id) {
        this.tag_classify_id = tag_classify_id;
    }

    public String getTag_classify_name() {
        return tag_classify_name;
    }

    public void setTag_classify_name(String tag_classify_name) {
        this.tag_classify_name = tag_classify_name;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getTag_classify_describe() {
        return tag_classify_describe;
    }

    public void setTag_classify_describe(String tag_classify_describe) {
        this.tag_classify_describe = tag_classify_describe;
    }

    @Override
    public String toString() {
        return "KsUserTagClassifyManage{" +
                "tag_classify_id=" + tag_classify_id +
                ", tag_classify_name=" + tag_classify_name +
                ", tag_classify_describe=" + tag_classify_describe +
                ", createtime=" + createtime +
                "}";
    }
}
