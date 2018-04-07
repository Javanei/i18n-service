package br.com.javanei.i18n.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "PROJECT", indexes = {
        @Index(name = "IDX_PROJECT_001", columnList = "COMPANY_ID, NAME"),
        @Index(name = "IDX_PROJECT_002", columnList = "COMPANY_ID, DEFAULT_LANGUAGE_ID"),
        @Index(name = "IDX_PROJECT_003", columnList = "COMPANY_ID, PARENT_PROJECT_ID")
})
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PROJECT_ID", length = 36, nullable = false, updatable = false)
    private String id;
    @Column(name = "NAME", length = 255, nullable = false)
    private String name;
    @Column(name = "COMPANY_ID", length = 36, nullable = false, updatable = false)
    private String companyId;

    @Column(name = "DEFAULT_LANGUAGE_ID", length = 36, nullable = false)
    private String defaultLanguageId;
    @Column(name = "PARENT_PROJECT_ID", length = 36)
    private String parentProjectId;

    public Project() {
    }

    public Project(String id) {
        this.id = id;
    }

    public Project(String name, String companyId, String defaultLanguageId) {
        this.name = name;
        this.companyId = companyId;
        this.defaultLanguageId = defaultLanguageId;
    }

    public Project(String id, String name, String companyId, String defaultLanguageId) {
        this.id = id;
        this.name = name;
        this.companyId = companyId;
        this.defaultLanguageId = defaultLanguageId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getDefaultLanguageId() {
        return defaultLanguageId;
    }

    public void setDefaultLanguageId(String defaultLanguageId) {
        this.defaultLanguageId = defaultLanguageId;
    }

    public String getParentProjectId() {
        return parentProjectId;
    }

    public void setParentProjectId(String parentProjectId) {
        this.parentProjectId = parentProjectId;
    }
}
