
package com.bootx.config;

import java.util.List;

import freemarker.template.TemplateMethodModelEx;
import org.apache.commons.lang3.StringUtils;

import freemarker.template.SimpleScalar;
import org.springframework.stereotype.Component;

/**
 * @author black
 */
@Component
public class UpperCaseMethod implements TemplateMethodModelEx {
	public Object exec(List arguments) {
		if (arguments != null && !arguments.isEmpty() && arguments.get(0) != null && StringUtils.isNotEmpty(arguments.get(0).toString())) {
			String str = arguments.get(0).toString();
			return new SimpleScalar(StringUtils.upperCase(str.substring(0,1))+str.substring(1));
		}
		return null;
	}
}