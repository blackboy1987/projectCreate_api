package com.bootx.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * @author black
 */
@Entity
public class Project extends BaseEntity<Long>{

    /**
     *
     */
    @JsonView({PageView.class})
    private String name;

    @JsonView({PageView.class})
    private String memo;

    @JsonView({PageView.class})
    private String packageName;

    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY)
    private Set<ProjectModule> modules = new HashSet<>();

    @NotNull
    @JoinColumn(nullable = false,updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Admin admin;

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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Transient
    @JsonView({PageView.class})
    public String getCreator(){
        if(admin!=null){
            return admin.getUsername();
        }
        return null;
    }
}
