package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * Created by stg on 2018/8/10.
 */
@TableName("ks_device_user")
public class KsDeviceUser {
    private int user_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    private String device_id;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }
}
