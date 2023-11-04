
package ${packageName}.controller;

import ${packageName}.Pageable;
import ${packageName}.common.Results;
import ${packageName}.entity.Brand;
import ${packageName}.BrandService;
import ${packageName}.common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.annotation.Resource;

/**
 * Controller - ${module.memo}
 * 
 * @author ${author}
 * @version ${version}
 */
@Controller
@RequestMapping("${module.apiUrl!}")
public class ${upperCase(module.name)}Controller extends BaseController {

	@Resource
	private ${upperCase(module.name)}Service ${lowerCase(module.name)}Service;

	/**
	 * 保存
	 */
	@PostMapping("/save")
	public Result save(${upperCase(module.name)} ${lowerCase(module.name)}) {
		${lowerCase(module.name)}Service.save(${lowerCase(module.name)});
		return Result.success();
	}

	/**
	 * 更新
	 */
	@PostMapping("/update")
	public Result update(${upperCase(module.name)} ${lowerCase(module.name)}) {
		${lowerCase(module.name)}Service.update(${lowerCase(module.name)});
		return Result.success();
	}

	/**
	 * 列表
	 */
	@GetMapping("/list")
	public Result list(Pageable pageable) {
		return Result.success(${lowerCase(module.name)}Service.findPage(pageable));
	}

	/**
	 * 删除
	 */
	@PostMapping("/delete")
	public Result delete(Long[] ids) {
		${lowerCase(module.name)}Service.delete(ids);
		return Result.success();
	}

}