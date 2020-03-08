package com.bjfu.ecloud.controller;

import com.alibaba.fastjson.JSONObject;

import com.bjfu.ecloud.entity.UserInfo;
import com.bjfu.ecloud.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthorizeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    @ResponseBody
    public JSONObject registerUser(@RequestBody Map<String,String> params){
        System.out.println("Enter register...");
        UserInfo user = new UserInfo();
        user.setUsername(params.get("username"));
        // 记得注册的时候把密码加密一下
        user.setPwd(bCryptPasswordEncoder.encode(params.get("pwd")));
        user.setUserRole("user");
        user.setTel(params.get("tel"));
        user.setEmail(params.get("email"));
        int rows = userInfoService.insert(user);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        jsonObject.put("id", user.getId());
        System.out.println(jsonObject);
        return jsonObject;
    }

//    @CrossOrigin("localhost:8090")
//    @PostMapping("/login")
//    @ResponseBody
//    public JSONObject login(@RequestBody JSONObject params){
//        System.out.println("Enter Post login...");
//        System.out.println("params = " + params);
//        System.out.println("add...");
//        logger.info("进入Post登录Controller...");
//        logger.warn("warn...");
//        logger.error("error");
//        System.out.println("over...");
//        JSONObject res = new JSONObject();
//        res.put("success", true);
//        return res;
//    }

//    @GetMapping("/login")
//    @ResponseBody JSONObject getLogin(@RequestBody JSONObject params){
//        System.out.println("Enter Get login...");
//
//        System.out.println("params = " + params);
//        logger.info("进入Get登录Controller...");
//        logger.warn("warn...");
//        logger.error("error");
//        JSONObject res = new JSONObject();
//        res.put("success", true);
//        return res;
//    }
}
