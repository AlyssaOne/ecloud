package com.bjfu.ecloud.entity;

public class DefaultPortrait {
    private Integer id;

    private String portrait;

    private String portraitType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait == null ? null : portrait.trim();
    }

    public String getPortraitType() {
        return portraitType;
    }

    public void setPortraitType(String portraitType) {
        this.portraitType = portraitType == null ? null : portraitType.trim();
    }
}