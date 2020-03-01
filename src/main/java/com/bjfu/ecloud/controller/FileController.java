package com.bjfu.ecloud.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ecloud")
public class FileController {

    @PostMapping("/files")
    @ResponseBody
    public JSONObject listFile(@RequestBody JSONObject params){
        System.out.println("Enter Post files...");
        System.out.println("params = " + params);
        JSONObject res = new JSONObject();
        res.put("success", true);
        return res;
    }
}
