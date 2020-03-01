package com.bjfu.ecloud.entity;

import java.util.Date;

public class PhysicalFile {
    private Integer id;

    private String physicalFileName;

    private String hadoopPath;

    private String physicalFileStatus;

    private String physicalFileSize;

    private String physicalFileType;

    private Date physicalFileRecentUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhysicalFileName() {
        return physicalFileName;
    }

    public void setPhysicalFileName(String physicalFileName) {
        this.physicalFileName = physicalFileName == null ? null : physicalFileName.trim();
    }

    public String getHadoopPath() {
        return hadoopPath;
    }

    public void setHadoopPath(String hadoopPath) {
        this.hadoopPath = hadoopPath == null ? null : hadoopPath.trim();
    }

    public String getPhysicalFileStatus() {
        return physicalFileStatus;
    }

    public void setPhysicalFileStatus(String physicalFileStatus) {
        this.physicalFileStatus = physicalFileStatus == null ? null : physicalFileStatus.trim();
    }

    public String getPhysicalFileSize() {
        return physicalFileSize;
    }

    public void setPhysicalFileSize(String physicalFileSize) {
        this.physicalFileSize = physicalFileSize == null ? null : physicalFileSize.trim();
    }

    public String getPhysicalFileType() {
        return physicalFileType;
    }

    public void setPhysicalFileType(String physicalFileType) {
        this.physicalFileType = physicalFileType == null ? null : physicalFileType.trim();
    }

    public Date getPhysicalFileRecentUpdate() {
        return physicalFileRecentUpdate;
    }

    public void setPhysicalFileRecentUpdate(Date physicalFileRecentUpdate) {
        this.physicalFileRecentUpdate = physicalFileRecentUpdate;
    }
}