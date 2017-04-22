package com.ekong.entity;

import java.util.Date;

/**
 * Created by xiao on 2017/3/23.
 */
public class BaseEntity {

    private Date createdTime;   //修改时间
    private Date lastModified;  //最后修改时间

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
