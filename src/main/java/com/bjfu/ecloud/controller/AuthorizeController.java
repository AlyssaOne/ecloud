package com.bjfu.ecloud.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/ecloud")
public class AuthorizeController {

//    @CrossOrigin("localhost:8090")
    @PostMapping("/login")
    @ResponseBody
    public JSONObject login(@RequestBody JSONObject params){
        System.out.println("Enter Post login...");
        System.out.println("params = " + params);
        JSONObject res = new JSONObject();
        res.put("success", true);
        return res;
    }

    @GetMapping("/login")
    @ResponseBody JSONObject getLogin(@RequestBody JSONObject params){
        System.out.println("Enter Get login...");
        System.out.println("params = " + params);
        JSONObject res = new JSONObject();
        res.put("success", true);
        return res;
    }
}
