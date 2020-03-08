package com.bjfu.ecloud.service.impl;

import com.bjfu.ecloud.mapper.VirtualFolderMapper;
import com.bjfu.ecloud.service.VirtualFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class VirtualFolderServiceImpl implements VirtualFolderService {

    @Autowired
    private VirtualFolderMapper virtualFolderMapper;

    @Override
    public List<HashMap> selectByParentVirtualFolderId(Integer id) {
        return virtualFolderMapper.selectByParentVirtualFolderId(id);
    }
}