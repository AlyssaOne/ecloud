package com.bjfu.ecloud.mapper;

import com.bjfu.ecloud.entity.Download;

public interface DownloadMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Download record);

    int insertSelective(Download record);

    Download selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Download record);

    int updateByPrimaryKey(Download record);
}