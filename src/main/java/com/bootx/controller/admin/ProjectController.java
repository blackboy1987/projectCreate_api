
package com.bootx.controller.admin;

import com.bootx.common.Pageable;
import com.bootx.common.Result;
import com.bootx.entity.Admin;
import com.bootx.entity.BaseEntity;
import com.bootx.entity.Project;
import com.bootx.security.CurrentUser;
import com.bootx.service.AdminService;
import com.bootx.service.ProjectService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @author black
 */
@RestController
@RequestMapping("/api/admin/project")
public class ProjectController extends BaseController {

	@Resource
	private ProjectService projectService;

	/**
	 * 保存
	 */
	@PostMapping("/save")
	public Result save(Project project, @CurrentUser Admin admin) {
		projectService.save(project);
		return Result.success();
	}

	/**
	 * 更新
	 */
	@PostMapping("/update")
	public Result update(Project project) {
		projectService.update(project);
		return Result.success();
	}

	/**
	 * 列表
	 */
	@PostMapping("/list")
	@JsonView(BaseEntity.PageView.class)
	public Result list(Pageable pageable, @CurrentUser Admin admin) {
		return Result.success(projectService.findPage(pageable));
	}

	/**
	 * 删除
	 */
	@PostMapping("/delete")
	public Result delete(Long[] ids) {
		projectService.delete(ids);
		return Result.success();
	}

}