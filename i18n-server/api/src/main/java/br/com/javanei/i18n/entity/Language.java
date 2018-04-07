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
@Table(name = "LANGUAGE", indexes = {
        @Index(name = "IDX_LANGUAGE_001", columnList = "COMPANY_ID, CODE", unique = true),
        @Index(name = "IDX_LANGUAGE_002", columnList = "COMPANY_ID, NAME", unique = true)
})
public class Language implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "LANGUAGE_ID", length = 36, nullable = false, updatable = false)
    private String id;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @Column(name = "CODE", length = 32, nullable = false)
    private String code;

    @Column(name = "NAME", length = 160, nullable = false)
    private String name;

    public Language() {
    }

    public Language(String id) {
        this.id = id;
    }

    public Language(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Language(String id, Company company, String code, String name) {
        this.id = id;
        this.company = company;
        this.code = code;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
