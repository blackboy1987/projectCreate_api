
package com.bootx.dao;


import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.entity.OptLog;

/**
 * @author black
 */
public interface OptLogDao extends BaseDao<OptLog, Long> {

	/**
	 * 删除所有
	 */
	void removeAll();

    Page<OptLog> findPage(Pageable pageable, String action, String username, String requestUrl);
}