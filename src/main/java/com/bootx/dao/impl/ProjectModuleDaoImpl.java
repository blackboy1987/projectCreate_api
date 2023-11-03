
package com.bootx.dao.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.dao.ProjectModuleDao;
import com.bootx.entity.Project;
import com.bootx.entity.ProjectModule;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

/**
 * @author black
 */
@Repository
public class ProjectModuleDaoImpl extends BaseDaoImpl<ProjectModule, Long> implements ProjectModuleDao {

    @Override
    public Page<ProjectModule> findPage(Pageable pageable, Project project) {
        if(project==null){
            return Page.emptyPage(pageable);
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();;
        CriteriaQuery<ProjectModule> criteriaQuery = criteriaBuilder.createQuery(ProjectModule.class);
        Root<ProjectModule> root = criteriaQuery.from(ProjectModule.class);
        criteriaQuery.select(root);
        Predicate conjunction = criteriaBuilder.conjunction();
        conjunction = criteriaBuilder.and(conjunction, criteriaBuilder.equal(root.get("project"),project));
        criteriaQuery.where(conjunction);
        return super.findPage(criteriaQuery,pageable);
    }
}