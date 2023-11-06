package com.bootx.service.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.dao.OptLogDao;
import com.bootx.entity.OptLog;
import com.bootx.service.OptLogService;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class OptLogServiceImpl extends BaseServiceImpl<OptLog, Long> implements OptLogService {

	@Resource
	private OptLogDao auditLogDao;

	@Override
	@Async
	public void create(OptLog optLog) {
		auditLogDao.persist(optLog);
	}

	@Override
	public void clear() {
		auditLogDao.removeAll();
	}

	@Override
	public Page<OptLog> findPage(Pageable pageable, String action, String username, String requestUrl) {
		return auditLogDao.findPage(pageable,action,username,requestUrl);
	}

}