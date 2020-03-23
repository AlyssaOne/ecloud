package com.bjfu.ecloud.service.impl;

import com.bjfu.ecloud.entity.VirtualFolder;
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
    public VirtualFolder selectByPrimaryKey(Integer id) {
        return virtualFolderMapper.selectByPrimaryKey(id);
    }

    @Override
    public VirtualFolder selectRootByUserId(Integer userId) {
        return virtualFolderMapper.selectRootByUserId(userId);
    }


    @Override
    public List<HashMap> selectByParentVirtualFolderId(Integer id) {
        return virtualFolderMapper.selectByParentVirtualFolderId(id);
    }

    @Override
    public List<HashMap> selectRootFoldersByUserId(Integer userId) {
        return virtualFolderMapper.selectRootFoldersByUserId(userId);
    }

    @Override
    public int insert(VirtualFolder record) {
        return virtualFolderMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKeySelective(VirtualFolder record) {
        return virtualFolderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return virtualFolderMapper.deleteByPrimaryKey(id);
    }


}
