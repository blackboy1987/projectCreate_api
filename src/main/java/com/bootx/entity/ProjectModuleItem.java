package com.bootx.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

/**
 * @author black
 */
@Entity
public class ProjectModuleItem extends BaseEntity<Long>{

    @ManyToOne(fetch = FetchType.LAZY)
    private ProjectModule module;

    private String filedName;

    private String comment;

    private String type;

    private Boolean isNull;

    private String defaultValue;

    public ProjectModule getModule() {
        return module;
    }

    public void setModule(ProjectModule module) {
        this.module = module;
    }

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getNull() {
        return isNull;
    }

    public void setNull(Boolean aNull) {
        isNull = aNull;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
