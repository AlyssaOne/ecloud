package com.bjfu.ecloud.service.impl;

import com.bjfu.ecloud.entity.VirtualFile;
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

    @Override
    public VirtualFile selectByPrimaryKey(Integer id) {
        return virtualFileMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(VirtualFile record) {
        return virtualFileMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKeySelective(VirtualFile record) {
        return virtualFileMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return virtualFileMapper.deleteByPrimaryKey(id);
    }
}
