package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

public class RespResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private int status; //状态 0 失败  1 成功
    private String msg;//消息

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
