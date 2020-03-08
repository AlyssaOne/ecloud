package com.bjfu.ecloud.service;

import java.util.HashMap;
import java.util.List;

public interface VirtualFolderService {

    List<HashMap> selectByParentVirtualFolderId(Integer id);
}
