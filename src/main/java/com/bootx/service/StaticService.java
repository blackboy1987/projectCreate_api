
package com.bootx.service;

import com.bootx.entity.ProjectModule;

import java.util.Map;

/**
 * @author black
 */
public interface StaticService {

	int build(String templatePath, String staticPath, Map<String, Object> model);

	int build(ProjectModule projectModule);
}