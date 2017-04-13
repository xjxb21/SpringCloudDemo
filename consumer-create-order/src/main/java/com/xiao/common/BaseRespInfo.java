package com.xiao.common;

/**
 * Created by xiao on 2017/3/23.
 */
public class BaseRespInfo {

    boolean status; //处理状态
    String message; //返回信息
    Object object;  //返回的信息

    public BaseRespInfo(boolean status, String message, Object object) {
        this.status = status;
        this.message = message;
        this.object = object;
    }

    public BaseRespInfo(){}

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}