package br.com.javanei.i18n.v1.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("ProjectUpdate")
public class ProjectUpdateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Project Name", required = true)
    private String name;

    public ProjectUpdateDTO() {
    }

    public ProjectUpdateDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProjectUpdateDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
