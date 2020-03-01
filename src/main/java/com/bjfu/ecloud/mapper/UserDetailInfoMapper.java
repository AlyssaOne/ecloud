package com.bjfu.ecloud.mapper;

import com.bjfu.ecloud.entity.UserDetailInfo;

public interface UserDetailInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDetailInfo record);

    int insertSelective(UserDetailInfo record);

    UserDetailInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserDetailInfo record);

    int updateByPrimaryKey(UserDetailInfo record);
}