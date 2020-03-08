package com.bjfu.ecloud.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bjfu.ecloud.service.VirtualFileService;
import com.bjfu.ecloud.service.VirtualFolderService;
import com.bjfu.ecloud.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ecloud")
public class FileController {

    @Autowired
    private VirtualFolderService virtualFolderService;

    @Autowired
    private VirtualFileService virtualFileService;

    @PostMapping("/onload")
    @ResponseBody
    public JSONObject onLoad(@RequestBody JSONObject params){
        String token = (String) params.get("token");
        String username = JwtTokenUtils.getUsername(token);

        JSONObject res = new JSONObject();
        res.put("success", true);
        return res;
    }

    /**
     * 查看对应文件夹下左右文件
     * @param params 包括token、点击的folder_id
     * @return
     */
    @PostMapping("/files")
    @ResponseBody
    public JSONObject listFile(@RequestBody JSONObject params) {
        System.out.println("Enter Post files...");
        System.out.println("params = " + params);
//        System.out.println("path = " + path);

        String token = (String) params.get("token");
        String username = JwtTokenUtils.getUsername(token);
//        System.out.println(username);
        Integer folder_id = Integer.parseInt(params.getString("folder_id"));

        List<HashMap> folders = virtualFolderService.selectByParentVirtualFolderId(folder_id);
        List<HashMap> files = virtualFileService.selectByParentVirtualFolderId(folder_id);

        JSONObject res = new JSONObject();
        res.put("success", true);
        res.put("folders", folders);
        res.put("files", files);
        return res;
    }

    @GetMapping("/files")
    @ResponseBody
    public JSONObject listGetFile(@RequestBody JSONObject params) {
        System.out.println("Enter Get files...");
        System.out.println("params = " + params);

        String token = (String) params.get("token");
        String username = JwtTokenUtils.getUsername(token);
        System.out.println(username);

        JSONObject res = new JSONObject();
        res.put("success", true);
        return res;
    }
}
