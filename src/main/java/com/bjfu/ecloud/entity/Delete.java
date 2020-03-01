package com.bjfu.ecloud.entity;

public class Delete {
    private Integer id;

    private Integer physicalFileId;

    private Integer userId;

    private String deleteFileStatus;

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

    public String getDeleteFileStatus() {
        return deleteFileStatus;
    }

    public void setDeleteFileStatus(String deleteFileStatus) {
        this.deleteFileStatus = deleteFileStatus == null ? null : deleteFileStatus.trim();
    }
}