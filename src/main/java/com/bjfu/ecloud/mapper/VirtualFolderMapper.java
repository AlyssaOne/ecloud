package com.bjfu.ecloud.mapper;

import com.bjfu.ecloud.entity.VirtualFolder;

public interface VirtualFolderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VirtualFolder record);

    int insertSelective(VirtualFolder record);

    VirtualFolder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VirtualFolder record);

    int updateByPrimaryKey(VirtualFolder record);
}