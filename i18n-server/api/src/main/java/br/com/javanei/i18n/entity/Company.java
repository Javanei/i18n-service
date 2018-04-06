package br.com.javanei.i18n.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "COMPANY", indexes = {
        @Index(name = "IDX_COMPANY_001", columnList = "NAME")
})
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "COMPANY_ID", length = 36, nullable = false, updatable = false)
    private String id;
    @Column(name = "NAME", length = 200, nullable = false)
    private String name;

    public Company() {
    }

    public Company(String id) {
        this.id = id;
    }

    public Company(String id, String name) {
        this.id = id;
        this.name = name;
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
}
