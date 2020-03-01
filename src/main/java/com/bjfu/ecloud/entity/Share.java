package com.bjfu.ecloud.entity;

public class Share {
    private Integer id;

    private Integer physicalFileId;

    private Integer senderId;

    private Integer recieverId;

    private String shareFileStatus;

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

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getRecieverId() {
        return recieverId;
    }

    public void setRecieverId(Integer recieverId) {
        this.recieverId = recieverId;
    }

    public String getShareFileStatus() {
        return shareFileStatus;
    }

    public void setShareFileStatus(String shareFileStatus) {
        this.shareFileStatus = shareFileStatus == null ? null : shareFileStatus.trim();
    }
}