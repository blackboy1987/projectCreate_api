package com.bootx.controller;

import com.bootx.common.Result;
import com.bootx.entity.ProjectModule;
import com.bootx.entity.ProjectModuleItem;
import com.bootx.service.StaticService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private StaticService staticService;

    @GetMapping
    public Result init(){
        ProjectModule projectModule = new ProjectModule();
        projectModule.setCreatedDate(new Date());
        projectModule.setLastModifiedDate(new Date());
        projectModule.setName("abc");

        List<ProjectModuleItem> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ProjectModuleItem projectModuleItem = new ProjectModuleItem();
            projectModuleItem.setFiledName("name"+i);
            if(i%5==0){
                projectModuleItem.setType("Long");
            }else if(i%5==1){
                projectModuleItem.setType("String");
            }else if(i%5==2){
                projectModuleItem.setType("Boolean");
            }else if(i%5==3){
                projectModuleItem.setType("Date");
            }else if(i%5==4){
                projectModuleItem.setType("Integer");
            }
            items.add(projectModuleItem);
        }
        projectModule.setItems(new HashSet<>(items));
        staticService.build(projectModule);
        return Result.success();
    }
}
