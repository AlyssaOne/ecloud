package com.bjfu.ecloud.mapper;

import com.bjfu.ecloud.entity.DefaultPortrait;

public interface DefaultPortraitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DefaultPortrait record);

    int insertSelective(DefaultPortrait record);

    DefaultPortrait selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DefaultPortrait record);

    int updateByPrimaryKey(DefaultPortrait record);
}