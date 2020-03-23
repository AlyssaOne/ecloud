package com.bjfu.ecloud.service.impl;

import com.bjfu.ecloud.entity.PhysicalFile;
import com.bjfu.ecloud.mapper.PhysicalFileMapper;
import com.bjfu.ecloud.service.PhysicalFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhysicalFileServiceImpl implements PhysicalFileService {

    @Autowired
    PhysicalFileMapper physicalFileMapper;

    @Override
    public PhysicalFile selectByPrimaryKey(Integer id) {
        return physicalFileMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(PhysicalFile record) {
        return physicalFileMapper.insert(record);
    }
}
