
package com.bootx.service.impl;

import com.bootx.common.Template;
import com.bootx.config.LowerCaseMethod;
import com.bootx.config.UpperCaseMethod;
import com.bootx.entity.ProjectModule;
import com.bootx.service.StaticService;
import com.bootx.service.TemplateService;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletContext;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author black
 */
@Service
public class StaticServiceImpl implements StaticService, ServletContextAware {

	private ServletContext servletContext;

	@Resource
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@Resource
	private TemplateService templateService;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Transactional(readOnly = true)
	public int build(String templatePath, String staticPath, Map<String, Object> model) {

		FileOutputStream fileOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		Writer writer = null;
		try {
			freemarker.template.Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templatePath);
			File staticFile = new File(SystemUtils.getJavaIoTmpDir(),staticPath);
			File staticDirectory = staticFile.getParentFile();
			if (!staticDirectory.exists()) {
				staticDirectory.mkdirs();
			}
			System.out.println(staticFile.getAbsolutePath());
			fileOutputStream = new FileOutputStream(staticFile);
			outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
			writer = new BufferedWriter(outputStreamWriter);
			template.process(model, writer);
			writer.flush();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(writer);
			IOUtils.closeQuietly(outputStreamWriter);
			IOUtils.closeQuietly(fileOutputStream);
		}
		return 0;
	}

	@Override
	public String build(ProjectModule projectModule) {
		String uuid = UUID.randomUUID().toString().replace("-","");
		Template entity = templateService.get("entity");
		Template dao = templateService.get("dao");
		Template daoImpl = templateService.get("daoImpl");
		Template service = templateService.get("service");
		Template serviceImpl = templateService.get("serviceImpl");
		Template controller = templateService.get("controller");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("module", projectModule);
		model.put("upperCase", new UpperCaseMethod());
		model.put("lowerCase", new LowerCaseMethod());
		model.put("author", "blackboy");
		model.put("version", "2.3");
		model.put("packageName", projectModule.getProject().getPackageName());
		model.put("importPackages", projectModule.getImportPackage());
		build(entity.getTemplatePath(), "/"+uuid+projectModule.getPath(entity.getStaticPath()), model);
		build(dao.getTemplatePath(), "/"+uuid+projectModule.getPath(dao.getStaticPath()), model);
		build(daoImpl.getTemplatePath(), "/"+uuid+projectModule.getPath(daoImpl.getStaticPath()), model);
		build(service.getTemplatePath(), "/"+uuid+projectModule.getPath(service.getStaticPath()), model);
		build(serviceImpl.getTemplatePath(), "/"+uuid+projectModule.getPath(serviceImpl.getStaticPath()), model);
		build(controller.getTemplatePath(), "/"+uuid+projectModule.getPath(controller.getStaticPath()), model);
		return uuid;
	}

}