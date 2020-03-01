package com.bjfu.ecloud.entity;

public class Download {
    private Integer id;

    private Integer physicalFileId;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPhysicalFileId() {
        return physicalFileId;
    }

    public void setPhysicalFileId(Integer physicalFileId) {
        this.physicalFileId = physicalFileId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}