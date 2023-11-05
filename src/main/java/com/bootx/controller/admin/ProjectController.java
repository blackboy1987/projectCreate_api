
package com.bootx.controller.admin;

import com.bootx.audit.Audit;
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
	public Result save(Project project) {
		project.setAdmin(adminService.getCurrent());
		projectService.save(project);
		return Result.success();
	}

	/**
	 * 更新
	 */
	@PostMapping("/update")
	public Result update(Project project) {
		Project parent = projectService.find(project.getId());
		Admin current = adminService.getCurrent();
		if(parent==null || parent.getAdmin() != current){
			return Result.error("项目不存在");
		}
		projectService.update(project,"admin");
		return Result.success();
	}

	/**
	 * 列表
	 */
	@PostMapping("/list")
	@Audit(action = "项目查询")
	@JsonView(BaseEntity.PageView.class)
	public Result list(Pageable pageable,String name) {
		return Result.success(projectService.findPage(pageable,adminService.getCurrent(),name));
	}

	/**
	 * 删除
	 */
	@PostMapping("/delete")
	public Result delete(Long[] ids) {
		for (Long id: ids){
			Project project = projectService.find(id);
			Admin current = adminService.getCurrent();
			if(project==null || project.getAdmin() != current){
				return Result.error("项目不存在");
			}else {
				projectService.delete(project);
			}
		}
		return Result.success();
	}

}