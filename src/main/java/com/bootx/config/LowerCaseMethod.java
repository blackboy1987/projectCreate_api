
package com.bootx.config;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author black
 */
@Component
public class LowerCaseMethod implements TemplateMethodModelEx {
	public Object exec(List arguments) {
		if (arguments != null && !arguments.isEmpty() && arguments.get(0) != null && StringUtils.isNotEmpty(arguments.get(0).toString())) {
			String str = arguments.get(0).toString();
			return new SimpleScalar(StringUtils.lowerCase(str.substring(0,1))+str.substring(1));
		}
		return null;
	}
}