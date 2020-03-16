package com.bjfu.ecloud.service.impl;

import com.bjfu.ecloud.mapper.VirtualFileMapper;
import com.bjfu.ecloud.service.VirtualFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class VirtualFileServiceImpl implements VirtualFileService {

    @Autowired
    private VirtualFileMapper virtualFileMapper;

    @Override
    public List<HashMap> selectByParentVirtualFolderId(Integer id) {
        return virtualFileMapper.selectByParentVirtualFolderId(id);
    }

    @Override
    public List<HashMap> selectRootFilesByUserId(Integer userId) {
        return virtualFileMapper.selectRootFilesByUserId(userId);
    }
}
