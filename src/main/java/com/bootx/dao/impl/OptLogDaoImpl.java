
package com.bootx.dao.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.dao.OptLogDao;
import com.bootx.entity.OptLog;
import com.bootx.entity.Project;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

@Repository
public class OptLogDaoImpl extends BaseDaoImpl<OptLog, Long> implements OptLogDao {

	@Override
	public void removeAll() {
		String jpql = "delete from OptLog optLog";
		entityManager.createQuery(jpql).executeUpdate();
	}

	@Override
	public Page<OptLog> findPage(Pageable pageable, String action, String username, String requestUrl) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<OptLog> criteriaQuery = criteriaBuilder.createQuery(OptLog.class);
		Root<OptLog> root = criteriaQuery.from(OptLog.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (StringUtils.isNotEmpty(action)) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("action"), action));
		}
		if (StringUtils.isNotBlank(username)) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(root.get("admin").get("username"), "%"+username+"%"));
		}
		if (StringUtils.isNotBlank(requestUrl)) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(root.get("requestUrl"), requestUrl));
		}
		criteriaQuery.where(restrictions);

		return super.findPage(criteriaQuery, pageable);
	}

}