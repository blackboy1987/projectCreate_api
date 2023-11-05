package com.bootx.service.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.dao.ProjectDao;
import com.bootx.entity.Admin;
import com.bootx.entity.Project;
import com.bootx.service.ProjectService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author black
 */
@Service
public class ProjectServiceImpl extends BaseServiceImpl<Project,Long> implements ProjectService {

    @Resource
    private ProjectDao projectDao;

    @Override
    public Page<Project> findPage(Pageable pageable, Admin admin,String name) {
        return projectDao.findPage(pageable,admin,name);
    }
}
