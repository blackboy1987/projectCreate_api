
package com.bootx.controller.admin;

import com.bootx.audit.Audit;
import com.bootx.common.Pageable;
import com.bootx.common.Result;
import com.bootx.entity.BaseEntity;
import com.bootx.entity.ProjectModule;
import com.bootx.entity.ProjectModuleItem;
import com.bootx.service.*;
import com.bootx.util.CompressUtils;
import com.bootx.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.annotation.Resource;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author black
 */
@RestController
@RequestMapping("/api/admin/optLog")
public class OptLogController extends BaseController {

	@Resource
	private OptLogService optLogService;

	/**
	 * 列表
	 */
	@PostMapping("/list")
	@Audit(action = "操作日志查询")
	@JsonView(BaseEntity.PageView.class)
	public Result list(Pageable pageable,String action,String username,String requestUrl) {
		return Result.success(optLogService.findPage(pageable,action,username,requestUrl));
	}
}