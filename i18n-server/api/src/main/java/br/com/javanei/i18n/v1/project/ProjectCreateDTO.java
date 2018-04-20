package br.com.javanei.i18n.v1.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("ProjectCreate")
public class ProjectCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Project Name", required = true)
    private String name;

    @ApiModelProperty(value = "ID of default language", required = true)
    private String defaultLanguageId;

    @ApiModelProperty(value = "ID of parent project", required = true)
    private String parentProjectId;

    //TODO: Quando OAuth, deve ser removido para pegar da sessão
    @ApiModelProperty(value = "Company ID", required = true)
    @Deprecated
    private String companyId;

    public ProjectCreateDTO() {
    }

    public ProjectCreateDTO(String name, String defaultLanguageId, String parentProjectId, String companyId) {
        this.name = name;
        this.defaultLanguageId = defaultLanguageId;
        this.parentProjectId = parentProjectId;
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "ProjectCreateDTO{" +
                "name='" + name + '\'' +
                ", defaultLanguageId='" + defaultLanguageId + '\'' +
                ", parentProjectId='" + parentProjectId + '\'' +
                '}';
    }
}
