
package com.bootx.controller.admin;

import com.bootx.common.Pageable;
import com.bootx.common.Result;
import com.bootx.entity.BaseEntity;
import com.bootx.entity.Project;
import com.bootx.entity.ProjectModule;
import com.bootx.service.ProjectModuleService;
import com.bootx.service.ProjectService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author black
 */
@RestController
@RequestMapping("/api/admin/projectModule")
public class ProjectModuleController extends BaseController {

	@Resource
	private ProjectService projectService;

	@Resource
	private ProjectModuleService projectModuleService;

	/**
	 * 保存
	 */
	@PostMapping("/save")
	public Result save(ProjectModule projectModule,Long projectId) {
		projectModule.setProject(projectService.find(projectId));
		projectModuleService.save(projectModule);
		return Result.success();
	}

	/**
	 * 更新
	 */
	@PostMapping("/update")
	public Result update(ProjectModule projectModule,Long projectId) {
		projectModule.setProject(projectService.find(projectId));
		projectModuleService.update(projectModule);
		return Result.success();
	}

	/**
	 * 列表
	 */
	@PostMapping("/list")
	@JsonView(BaseEntity.PageView.class)
	public Result list(Pageable pageable,Long projectId) {
		return Result.success(projectModuleService.findPage(pageable,projectService.find(projectId)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/delete")
	public Result delete(Long[] ids) {
		projectModuleService.delete(ids);
		return Result.success();
	}

}