package com.bjfu.ecloud.mapper;

import com.bjfu.ecloud.entity.VirtualFolder;

import java.util.HashMap;
import java.util.List;

public interface VirtualFolderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VirtualFolder record);

    int insertSelective(VirtualFolder record);

    VirtualFolder selectByPrimaryKey(Integer id);

    List<HashMap> selectByParentVirtualFolderId(Integer id);

    List<HashMap> selectRootFoldersByUserId(Integer userId);

    int updateByPrimaryKeySelective(VirtualFolder record);

    int updateByPrimaryKey(VirtualFolder record);
}