
package com.bootx.controller.admin;

import com.bootx.common.Pageable;
import com.bootx.common.Result;
import com.bootx.entity.BaseEntity;
import com.bootx.entity.ProjectModule;
import com.bootx.entity.ProjectModuleItem;
import com.bootx.service.ProjectModuleItemService;
import com.bootx.service.ProjectModuleService;
import com.bootx.service.ProjectService;
import com.bootx.service.StaticService;
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
import java.io.IOException;

/**
 * @author black
 */
@RestController
@RequestMapping("/api/admin/projectModule")
public class ProjectModuleController extends BaseController {

	@Resource
	private StaticService staticService;
	@Resource
	private ProjectService projectService;

	@Resource
	private ProjectModuleService projectModuleService;

	@Resource
	private ProjectModuleItemService projectModuleItemService;

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

	@PostMapping("/items")
	public Result items(Long projectModuleId) {
		return Result.success(jdbcTemplate.queryForList("select id,filed_name filedName,type,comment from project_module_item where module_id=?",projectModuleId));
	}

	@PostMapping("/itemsSave")
	public Result itemsSave(Long projectModuleId,ProjectModuleItem projectModuleItem) {
		ProjectModule projectModule = projectModuleService.find(projectModuleId);
		projectModuleItem.setModule(projectModule);
		if(projectModuleItem.isNew()){
			projectModuleItemService.save(projectModuleItem);
		}else{
			projectModuleItemService.update(projectModuleItem);
		}
		return Result.success();

	}


	@PostMapping("/build")
	public ResponseEntity<InputStreamResource> build(Long id) throws IOException {
		ProjectModule projectModule = projectModuleService.find(id);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", projectModule.getName()+".zip"));
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		String uuid = staticService.build(projectModule);
		String now = DateUtils.formatDateToString(projectModule.getLastModifiedDate(),"yyyyMM");
		File destFile = new File(now+"_"+projectModule.getName()+".zip");
		File file = new File(SystemUtils.getJavaIoTmpDir()+"/"+uuid+"/"+now);
		File[] fileList = file.listFiles();
		CompressUtils.archive(fileList,destFile,"zip");
		System.out.println(destFile.getAbsolutePath());
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentLength(destFile.length())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(new InputStreamResource(FileUtils.openInputStream(destFile)));
	}
}