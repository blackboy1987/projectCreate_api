package com.bootx.service;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.entity.Project;
import com.bootx.entity.ProjectModule;

/**
 * @author black
 */
public interface ProjectModuleService extends BaseService<ProjectModule,Long> {
    Page<ProjectModule> findPage(Pageable pageable, Project project);
}
