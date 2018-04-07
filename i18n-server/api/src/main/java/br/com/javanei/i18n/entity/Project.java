package br.com.javanei.i18n.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @Column(name = "NAME", length = 200, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, optional = true)
    @JoinColumn(name = "DEFAULT_LANGUAGE_ID", referencedColumnName = "LANGUAGE_ID")
    private Language defaultLanguage;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, optional = true)
    @JoinColumn(name = "PARENT_PROJECT_ID", referencedColumnName = "PROJECT_ID")
    private Project parentProject;

    public Project() {
    }

    public Project(String id) {
        this.id = id;
    }

    public Project(String name, Language defaultLanguage) {
        this.name = name;
        this.defaultLanguage = defaultLanguage;
    }

    public Project(String id, Company company, String name, Language defaultLanguage, Project parentProject) {
        this.id = id;
        this.company = company;
        this.name = name;
        this.defaultLanguage = defaultLanguage;
        this.parentProject = parentProject;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Language getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(Language defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public Project getParentProject() {
        return parentProject;
    }

    public void setParentProject(Project parentProject) {
        this.parentProject = parentProject;
    }
}
