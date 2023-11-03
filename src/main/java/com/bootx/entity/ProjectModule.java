package com.bootx.entity;

import com.bootx.util.FreeMarkerUtils;
import com.bootx.util.SystemUtils;
import freemarker.template.TemplateException;
import jakarta.persistence.*;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author black
 */
@Entity
public class ProjectModule extends BaseEntity<Long>{

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @OneToMany(mappedBy = "module",fetch = FetchType.LAZY)
    private Set<ProjectModuleItem> items = new HashSet<>();

    private static String staticPath;

    public static String getStaticPath() {
        return staticPath;
    }

    public static void setStaticPath(String staticPath) {
        ProjectModule.staticPath = staticPath;
    }

    static {
        try {
            Document document = new SAXReader().read(SystemUtils.class.getResourceAsStream("/shopxx.xml"));
            org.dom4j.Element element = (org.dom4j.Element) document.selectSingleNode("/shopxx/template[@id='articleContent']");
            staticPath = element.attributeValue("staticPath");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Set<ProjectModuleItem> getItems() {
        return items;
    }

    public void setItems(Set<ProjectModuleItem> items) {
        this.items = items;
    }

    @Transient
    public String getPath() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", getId());
        model.put("createDate", getCreatedDate());
        model.put("modifyDate", getLastModifiedDate());
        try {
            return FreeMarkerUtils.process(staticPath, model);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
