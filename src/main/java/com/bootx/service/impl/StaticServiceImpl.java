
package com.bootx.service.impl;

import com.bootx.common.Template;
import com.bootx.entity.ProjectModule;
import com.bootx.service.StaticService;
import com.bootx.service.TemplateService;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletContext;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

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
			File staticFile = new File(servletContext.getRealPath(staticPath));
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
	public int build(ProjectModule projectModule) {
		Template template = templateService.get("articleContent");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("module", projectModule);
		build(template.getTemplatePath(), projectModule.getPath(), model);
		return 1;
	}

}