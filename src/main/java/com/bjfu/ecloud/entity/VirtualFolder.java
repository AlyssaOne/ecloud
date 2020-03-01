package com.bjfu.ecloud.entity;

import java.util.Date;

public class VirtualFolder {
    private Integer id;

    private Integer parentFolderId;

    private String folderName;

    private String folderStatus;

    private String folderSize;

    private Date folderRecentUpdate;

    private Date folderCreate;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentFolderId() {
        return parentFolderId;
    }

    public void setParentFolderId(Integer parentFolderId) {
        this.parentFolderId = parentFolderId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName == null ? null : folderName.trim();
    }

    public String getFolderStatus() {
        return folderStatus;
    }

    public void setFolderStatus(String folderStatus) {
        this.folderStatus = folderStatus == null ? null : folderStatus.trim();
    }

    public String getFolderSize() {
        return folderSize;
    }

    public void setFolderSize(String folderSize) {
        this.folderSize = folderSize == null ? null : folderSize.trim();
    }

    public Date getFolderRecentUpdate() {
        return folderRecentUpdate;
    }

    public void setFolderRecentUpdate(Date folderRecentUpdate) {
        this.folderRecentUpdate = folderRecentUpdate;
    }

    public Date getFolderCreate() {
        return folderCreate;
    }

    public void setFolderCreate(Date folderCreate) {
        this.folderCreate = folderCreate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}