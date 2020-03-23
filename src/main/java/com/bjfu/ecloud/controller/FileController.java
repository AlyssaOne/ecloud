package com.bjfu.ecloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.bjfu.ecloud.entity.PhysicalFile;
import com.bjfu.ecloud.entity.UserInfo;
import com.bjfu.ecloud.entity.VirtualFile;
import com.bjfu.ecloud.entity.VirtualFolder;
import com.bjfu.ecloud.service.PhysicalFileService;
import com.bjfu.ecloud.service.UserInfoService;
import com.bjfu.ecloud.service.VirtualFileService;
import com.bjfu.ecloud.service.VirtualFolderService;
import com.bjfu.ecloud.util.FileDownloadUtils;
import com.bjfu.ecloud.util.FileSizeUtils;
import com.bjfu.ecloud.util.FileTypeUtils;
import com.bjfu.ecloud.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Controller
@RequestMapping("/ecloud")
public class FileController {

    @Autowired
    private VirtualFolderService virtualFolderService;

    @Autowired
    private VirtualFileService virtualFileService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PhysicalFileService physicalFileService;

    /**
     * 初始化页面时调用初始化方法，返回根目录下的文件
     *
     * @param params 包含token
     * @return 初始化页面所需数据
     */
    @PostMapping("/files_onload")
    @ResponseBody
    public JSONObject onLoad(@RequestBody JSONObject params) {
        String token = (String) params.get("token");
        String username = JwtTokenUtils.getUsername(token);

        UserInfo user = userInfoService.selectByUsername(username);

        VirtualFolder virtualFolder = virtualFolderService.selectRootByUserId(user.getId());

        List<HashMap> files = virtualFileService.selectRootFilesByUserId(user.getId());
        List<HashMap> folders = virtualFolderService.selectRootFoldersByUserId(user.getId());

        JSONObject res = new JSONObject();
        res.put("success", true);
        res.put("folders", folders);
        res.put("files", files);
        res.put("parent_id", virtualFolder.getId());
        res.put("parent_name", virtualFolder.getFolderName());
        return res;
    }

    /**
     * 查看对应文件夹下左右文件
     *
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
        Integer folderId = Integer.parseInt(params.getString("folder_id"));

        VirtualFolder virtualFolder = virtualFolderService.selectByPrimaryKey(folderId);

        List<HashMap> folders = virtualFolderService.selectByParentVirtualFolderId(folderId);
        List<HashMap> files = virtualFileService.selectByParentVirtualFolderId(folderId);

        JSONObject res = new JSONObject();
        res.put("success", true);
        res.put("folders", folders);
        res.put("files", files);
        res.put("parent_id", virtualFolder.getId());
        res.put("parent_name", virtualFolder.getFolderName());
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

    @PostMapping("/upload")
    @ResponseBody
    public JSONObject uploadFiles(@RequestParam("token") String token, @RequestParam("parentFolderId") String parentFolderId, @RequestParam("files") MultipartFile[] uploadFiles) {
        System.out.println("Enter upload files...");
        System.out.println("params = " + uploadFiles);

//        System.out.println(parentId);

//        String[] tmp = parentId.split("ulList");
//        System.out.println(tmp);
//        System.out.println(tmp[0]);
//        System.out.println(tmp[1]);
//        int parentFolderId = Integer.valueOf(tmp[1]);
        System.out.println(parentFolderId);

        String username = JwtTokenUtils.getUsername(token);
        UserInfo user = userInfoService.selectByUsername(username);
        System.out.println(uploadFiles.length);
//        MultipartFile file = uploadFiles;

        for (MultipartFile file : uploadFiles) {
            //处理文件数据
            String fileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String type = file.getContentType();
            long size = file.getSize();
            System.out.println(fileName + " " + type + " " + size);
            String[] tmp = fileName.split("\\.");
            for (String t : tmp)
                System.out.println(t);
            String destination = "/home/alyssa/Documents/graduation/cloud-disk";
            String absolutePathFileName = destination + "/" + uuid + "." + tmp[1];
            String physicalFileName = uuid + "." + tmp[1];
            try (InputStream inputStream = file.getInputStream();
                 FileOutputStream fileOutputStream = new FileOutputStream(absolutePathFileName);) {
                int t;
                byte[] buff = new byte[1024];
                while ((t = inputStream.read(buff)) != -1) {
                    fileOutputStream.write(buff);
                    fileOutputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            simpleDateFormat.format(new Date());
            String fileType = FileTypeUtils.getFileType(physicalFileName);
            String fileSize = FileSizeUtils.formatFileSize(size);

            //入库
            PhysicalFile physicalFile = new PhysicalFile();
            physicalFile.setPhysicalFileName(physicalFileName);
            physicalFile.setHadoopPath(destination);
            physicalFile.setPhysicalFileRecentUpdate(new Date());
            physicalFile.setPhysicalFileStatus("0");//设置为默认状态，可用
            physicalFile.setPhysicalFileType(fileType);
            physicalFile.setPhysicalFileSize(fileSize);
            physicalFileService.insert(physicalFile);

            VirtualFile virtualFile = new VirtualFile();
            virtualFile.setFileName(fileName);
            virtualFile.setFileAuthority("0");//设置默认权限为私密状态
            virtualFile.setFileRecentUpdate(new Date());
            virtualFile.setFileSize(fileSize);
            virtualFile.setFileType(fileType);
            virtualFile.setPhysicalFileId(physicalFile.getId());
            virtualFile.setUserId(user.getId());
            virtualFile.setVirtualFileStatus("0");//设置为默认可用状态
            virtualFile.setVirtualFolderId(Integer.valueOf(parentFolderId));
            virtualFileService.insert(virtualFile);

            System.out.println("insert successful!!");
        }

        List<HashMap> folders = virtualFolderService.selectByParentVirtualFolderId(Integer.valueOf(parentFolderId));
        List<HashMap> files = virtualFileService.selectByParentVirtualFolderId(Integer.valueOf(parentFolderId));

        JSONObject res = new JSONObject();
        res.put("success", true);
        res.put("fileNum", uploadFiles.length);
        res.put("folders", folders);
        res.put("files", files);
        return res;
    }

    @PostMapping("/create_folder")
    @ResponseBody
    public JSONObject createFolder(@RequestBody JSONObject params) {
        String token = (String) params.get("token");
        String prentFolderIdStr = (String) params.get("parentFolderId");
        String folderName = params.getString("folderName");
        String username = JwtTokenUtils.getUsername(token);
        Integer parentFolderId = Integer.valueOf(prentFolderIdStr);
        UserInfo user = userInfoService.selectByUsername(username);

        VirtualFolder virtualFolder = new VirtualFolder();
        virtualFolder.setFolderName(folderName);
        virtualFolder.setFolderCreate(new Date());
        virtualFolder.setFolderRecentUpdate(new Date());
        virtualFolder.setFolderStatus("0");//默认可用的状态
        virtualFolder.setParentFolderId(parentFolderId);
        virtualFolder.setVirtualPath("-");
        virtualFolder.setUserId(user.getId());

        virtualFolderService.insert(virtualFolder);

        List<HashMap> folders = virtualFolderService.selectByParentVirtualFolderId(Integer.valueOf(parentFolderId));
        List<HashMap> files = virtualFileService.selectByParentVirtualFolderId(Integer.valueOf(parentFolderId));

        JSONObject res = new JSONObject();
        res.put("success", true);
        res.put("folders", folders);
        res.put("files", files);
        return res;
    }

    @GetMapping("/download")
    /**
     * 暂不支持文件夹下载
     */
    public void downloadFiles(String fileId, boolean isFolder, HttpServletResponse response, HttpServletRequest request){
        VirtualFile virtualFile = virtualFileService.selectByPrimaryKey(Integer.valueOf(fileId));
        System.out.println(virtualFile);
        PhysicalFile physicalFile = physicalFileService.selectByPrimaryKey(virtualFile.getPhysicalFileId());
        System.out.println(physicalFile);

        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;");
        try {
            String fileDestination = physicalFile.getHadoopPath()+"/"+physicalFile.getPhysicalFileName();
            System.out.println(fileDestination);
            FileDownloadUtils.writeBytes(fileDestination, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/rename")
    @ResponseBody
    public JSONObject renameFile(@RequestParam("type") String type, @RequestParam("originId") Integer originId, @RequestParam("originName") String originName){
        JSONObject res = new JSONObject();

//        String type = (String) params.get("type");
//        Integer originId = (Integer) params.get("originId");
//        String originName = (String) params.get("originName");
        if(type.trim().equals("folder")){
            VirtualFolder virtualFolder = new VirtualFolder();
            virtualFolder.setId(originId);
            virtualFolder.setFolderName(originName);
            virtualFolderService.updateByPrimaryKeySelective(virtualFolder);
            System.out.println(virtualFolder.getFolderName()+" "+virtualFolder.getVirtualPath());
        }else if(type.trim().equals("file")){
            VirtualFile virtualFile = new VirtualFile();
            virtualFile.setId(originId);
            virtualFile.setFileName(originName);
            virtualFileService.updateByPrimaryKeySelective(virtualFile);
            System.out.println(virtualFile.getFileName()+" "+virtualFile.getFileSize());
        }else {
            res.put("success", false);
            return res;
        }
        res.put("success", true);
        return res;
    }

    @DeleteMapping("/file/{type}/{id}")
    @ResponseBody
    public JSONObject deleteFile(@PathVariable("type") String type, @PathVariable("id") Integer id){
        JSONObject res = new JSONObject();

//        String type = (String) params.get("type");
//        Integer originId = (Integer) params.get("originId");
//        String originName = (String) params.get("originName");
        if(type.trim().equals("folder")){
            virtualFolderService.deleteByPrimaryKey(id);
        }else if(type.trim().equals("file")){
            virtualFileService.deleteByPrimaryKey(id);
        }else {
            res.put("success", false);
            return res;
        }
        res.put("success", true);
        return res;
    }
}
