package com.bjfu.ecloud.mapper;

import com.bjfu.ecloud.entity.Delete;

public interface DeleteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Delete record);

    int insertSelective(Delete record);

    Delete selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Delete record);

    int updateByPrimaryKey(Delete record);
}