package com.bootx.service.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.dao.ProjectModuleDao;
import com.bootx.entity.Project;
import com.bootx.entity.ProjectModule;
import com.bootx.service.ProjectModuleService;
import com.bootx.service.ProjectService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author black
 */
@Service
public class ProjectModuleServiceImpl extends BaseServiceImpl<ProjectModule,Long> implements ProjectModuleService {

    @Resource
    private ProjectModuleDao projectModuleDao;

    @Override
    public Page<ProjectModule> findPage(Pageable pageable, Project project) {
        return projectModuleDao.findPage(pageable,project);
    }
}
