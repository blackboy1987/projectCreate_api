
package com.bootx.service.impl;

import com.bootx.common.Template;
import com.bootx.service.TemplateService;
import com.bootx.util.SystemUtils;
import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService {

	public List<Template> getAll() {
		try {
			Document document = new SAXReader().read(SystemUtils.class.getResourceAsStream("/shopxx.xml"));
			List<Template> templates = new ArrayList<Template>();
			List<Node> nodes = document.selectNodes("/shopxx/template");
			for (Node node : nodes) {
				Template template = getTemplate((Element) node);
				templates.add(template);
			}
			return templates;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Template> getList(Template.Type type) {
		if (type != null) {
			try {
				Document document = new SAXReader().read(SystemUtils.class.getResourceAsStream("/shopxx.xml"));
				List<Template> templates = new ArrayList<Template>();
				List<Node> nodes = document.selectNodes("/shopxx/template[@type='" + type + "']");
				for (Node node : nodes) {
					Template template = getTemplate((Element) node);
					templates.add(template);
				}
				return templates;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return getAll();
		}
	}

	@Cacheable("template")
	public Template get(String id) {
		try {
			Document document = new SAXReader().read(SystemUtils.class.getResourceAsStream("/shopxx.xml"));
			Element element = (Element) document.selectSingleNode("/shopxx/template[@id='" + id + "']");
            return getTemplate(element);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String read(String id) {
		Template template = get(id);
		return read(template);
	}

	public String read(Template template) {
		File templateFile = new File(template.getTemplatePath());
		String templateContent = null;
		try {
			templateContent = FileUtils.readFileToString(templateFile, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return templateContent;
	}

	public void write(String id, String content) {
		Template template = get(id);
		write(template, content);
	}

	public void write(Template template, String content) {
		File templateFile = new File(template.getTemplatePath());
		try {
			FileUtils.writeStringToFile(templateFile, content, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Template getTemplate(Element element) {
		String id = element.attributeValue("id");
		String type = element.attributeValue("type");
		String name = element.attributeValue("name");
		String templatePath = element.attributeValue("templatePath");
		String staticPath = element.attributeValue("staticPath");
		String description = element.attributeValue("description");

		Template template = new Template();
		template.setId(id);
		template.setType(Template.Type.valueOf(type));
		template.setName(name);
		template.setTemplatePath(templatePath);
		template.setStaticPath(staticPath);
		template.setDescription(description);
		return template;
	}

}