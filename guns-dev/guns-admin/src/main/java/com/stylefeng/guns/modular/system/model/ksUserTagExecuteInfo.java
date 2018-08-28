package com.stylefeng.guns.modular.system.model;

public class ksUserTagExecuteInfo {
    private int id;
    private int tag_id;
    private int datasource_id;
    private String executetime;
    private String datacount;
    private String tag_classify_name;
    private String tag_name;
    private String tag_value;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public int getDatasource_id() {
        return datasource_id;
    }

    public void setDatasource_id(int datasource_id) {
        this.datasource_id = datasource_id;
    }

    public String getExecutetime() {
        return executetime;
    }

    public void setExecutetime(String executetime) {
        this.executetime = executetime;
    }

    public String getDatacount() {
        return datacount;
    }

    public void setDatacount(String datacount) {
        this.datacount = datacount;
    }

}
