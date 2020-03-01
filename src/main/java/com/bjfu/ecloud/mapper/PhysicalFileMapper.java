package com.bjfu.ecloud.mapper;

import com.bjfu.ecloud.entity.PhysicalFile;

public interface PhysicalFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PhysicalFile record);

    int insertSelective(PhysicalFile record);

    PhysicalFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PhysicalFile record);

    int updateByPrimaryKey(PhysicalFile record);
}