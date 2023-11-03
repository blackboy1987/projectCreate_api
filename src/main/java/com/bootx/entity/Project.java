package com.bootx.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

/**
 * @author black
 */
@Entity
public class Project extends BaseEntity<Long>{

    private String name;

    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY)
    private Set<ProjectModule> modules = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProjectModule> getModules() {
        return modules;
    }

    public void setModules(Set<ProjectModule> modules) {
        this.modules = modules;
    }
}
