package com.bjfu.ecloud.entity;

import java.util.Date;

public class VirtualFile {
    private Integer id;

    private Integer physicalFileId;

    private Integer userId;

    private Integer virtualFolderId;

    private String fileName;

    private String virtualFileStatus;

    private String fileSize;

    private String fileType;

    private Date fileRecentUpdate;

    private String fileAuthority;

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

    public Integer getVirtualFolderId() {
        return virtualFolderId;
    }

    public void setVirtualFolderId(Integer virtualFolderId) {
        this.virtualFolderId = virtualFolderId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getVirtualFileStatus() {
        return virtualFileStatus;
    }

    public void setVirtualFileStatus(String virtualFileStatus) {
        this.virtualFileStatus = virtualFileStatus == null ? null : virtualFileStatus.trim();
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize == null ? null : fileSize.trim();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public Date getFileRecentUpdate() {
        return fileRecentUpdate;
    }

    public void setFileRecentUpdate(Date fileRecentUpdate) {
        this.fileRecentUpdate = fileRecentUpdate;
    }

    public String getFileAuthority() {
        return fileAuthority;
    }

    public void setFileAuthority(String fileAuthority) {
        this.fileAuthority = fileAuthority == null ? null : fileAuthority.trim();
    }
}