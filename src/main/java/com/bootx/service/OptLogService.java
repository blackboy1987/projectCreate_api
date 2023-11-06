
package com.bootx.service;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.entity.OptLog;

public interface OptLogService extends BaseService<OptLog, Long> {

	/**
	 * 创建审计日志(异步)
	 * 
	 * @param optLog
	 *            审计日志
	 */
	void create(OptLog optLog);

	/**
	 * 清空审计日志
	 */
	void clear();

	Page<OptLog> findPage(Pageable pageable, String action, String username, String requestUrl);
}