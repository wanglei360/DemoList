package com.yoland.adatepicker.bandu;

import java.io.Serializable;

/**
 * 创建者：admin
 * <p>时间：2017/3/14 14:31
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class BaseBean implements Serializable {
    protected String msg;
    protected int status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
