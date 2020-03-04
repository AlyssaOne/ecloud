package com.bjfu.ecloud.service.impl;

import com.bjfu.ecloud.entity.UserInfo;
import com.bjfu.ecloud.mapper.UserInfoMapper;
import com.bjfu.ecloud.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo selectByPrimaryKey(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserInfo selectByUsername(String username){
        return userInfoMapper.selectByUsername(username);
    }

    @Override
    public int insert(UserInfo record) {
        return userInfoMapper.insert(record);
    }
}
