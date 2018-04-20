package br.com.javanei.i18n.v1.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("ProjectResponse")
public class ProjectResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Project unique ID", required = true)
    private String id;

    @ApiModelProperty(value = "Project Name", required = true)
    private String name;

    @ApiModelProperty(value = "ID of default language", required = true)
    private String defaultLanguageId;

    @ApiModelProperty(value = "ID of parent project", required = true)
    private String parentProjectId;

    public ProjectResponseDTO() {
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

    @Override
    public String toString() {
        return "ProjectResponseDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", defaultLanguageId='" + defaultLanguageId + '\'' +
                ", parentProjectId='" + parentProjectId + '\'' +
                '}';
    }
}
