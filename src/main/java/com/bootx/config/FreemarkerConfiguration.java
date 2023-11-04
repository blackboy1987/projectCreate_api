package com.bootx.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author black
 */
@Configuration
public class FreemarkerConfiguration {
    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Resource
    private UpperCaseMethod upperCaseMethod;
    @PostConstruct
    public void configuration() {
        // 添加全局变量
        Map<String,Object> freemarkerVariables = new HashMap<>();
        freemarkerVariables.put("upperCase",upperCaseMethod);
        freeMarkerConfigurer.setFreemarkerVariables(freemarkerVariables);
    }
}
