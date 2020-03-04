package com.bjfu.ecloud.service.impl;

import com.bjfu.ecloud.entity.UserInfo;
import com.bjfu.ecloud.entity.security.JwtUser;
import com.bjfu.ecloud.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//用户安全service
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo user = userInfoService.selectByPrimaryKey(Integer.parseInt(s));
        return new JwtUser(user);
    }
}
