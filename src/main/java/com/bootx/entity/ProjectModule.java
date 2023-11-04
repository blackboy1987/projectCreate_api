package com.bootx.entity;

import com.bootx.util.FreeMarkerUtils;
import com.bootx.util.SystemUtils;
import com.fasterxml.jackson.annotation.JsonView;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateException;
import jakarta.persistence.*;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.util.*;

/**
 * @author black
 */
@Entity
public class ProjectModule extends BaseEntity<Long>{

    @JsonView({PageView.class})
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @OneToMany(mappedBy = "module",fetch = FetchType.LAZY)
    private Set<ProjectModuleItem> items = new HashSet<>();

    @JsonView({PageView.class})
    private String memo;

    @JsonView({PageView.class})
    private String tableName;

    @JsonView({PageView.class})
    private String apiUrl;

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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Transient
    public String getPath(String staticPath) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", upperCase(getName()));
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

    private String upperCase(String name) {
        return StringUtils.upperCase(name.substring(0,1))+name.substring(1);
    }

    public Set<String> getImportPackage(){
        Set<String> importPages = new HashSet<>();
        Set<ProjectModuleItem> items = getItems();
        for (ProjectModuleItem item:items) {
            if(StringUtils.equalsIgnoreCase("Date",item.getType())){
                importPages.add("import java.util.Date");
            }else if(StringUtils.equalsIgnoreCase("BigDecimal",item.getType())){
                importPages.add("import java.math.BigDecimal");
            }
        }
        return importPages;
    }
}
