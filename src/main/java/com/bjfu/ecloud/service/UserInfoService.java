package com.bjfu.ecloud.service;

import com.bjfu.ecloud.entity.UserInfo;

public interface UserInfoService {

    UserInfo selectByPrimaryKey(Integer id);

    int insert(UserInfo record);

    UserInfo selectByUsername(String username);
}
