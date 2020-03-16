package com.bjfu.ecloud.service;

import com.bjfu.ecloud.entity.VirtualFolder;

import java.util.HashMap;
import java.util.List;

public interface VirtualFolderService {

    VirtualFolder selectByPrimaryKey(Integer id);

    VirtualFolder selectRootByUserId(Integer userId);

    List<HashMap> selectByParentVirtualFolderId(Integer id);

    List<HashMap> selectRootFoldersByUserId(Integer userId);
}
