package com.bjfu.ecloud.mapper;

import com.bjfu.ecloud.entity.VirtualFile;

public interface VirtualFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VirtualFile record);

    int insertSelective(VirtualFile record);

    VirtualFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VirtualFile record);

    int updateByPrimaryKey(VirtualFile record);
}