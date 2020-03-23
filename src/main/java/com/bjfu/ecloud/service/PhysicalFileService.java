package com.bjfu.ecloud.service;

import com.bjfu.ecloud.entity.PhysicalFile;

public interface PhysicalFileService {

    PhysicalFile selectByPrimaryKey(Integer id);

    int insert(PhysicalFile record);
}
