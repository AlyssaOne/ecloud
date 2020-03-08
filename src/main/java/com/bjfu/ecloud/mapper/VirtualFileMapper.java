package com.bjfu.ecloud.mapper;

import com.bjfu.ecloud.entity.VirtualFile;

import java.util.HashMap;
import java.util.List;

public interface VirtualFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VirtualFile record);

    int insertSelective(VirtualFile record);

    VirtualFile selectByPrimaryKey(Integer id);

    List<HashMap> selectByParentVirtualFolderId(Integer id);

    List<HashMap> selectRootFilesByUserId(Integer userId);

    int updateByPrimaryKeySelective(VirtualFile record);

    int updateByPrimaryKey(VirtualFile record);
}