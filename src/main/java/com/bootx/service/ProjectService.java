package com.bootx.service;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.entity.Admin;
import com.bootx.entity.Project;

/**
 * @author black
 */
public interface ProjectService extends BaseService<Project,Long> {
    Page<Project> findPage(Pageable pageable, Admin admin,String name);
}
