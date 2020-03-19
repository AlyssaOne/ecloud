package com.bjfu.ecloud.service;

import com.bjfu.ecloud.entity.VirtualFile;

import java.util.HashMap;
import java.util.List;

public interface VirtualFileService {

    List<HashMap> selectByParentVirtualFolderId(Integer id);

    List<HashMap> selectRootFilesByUserId(Integer userId);

    int insert(VirtualFile record);
}
