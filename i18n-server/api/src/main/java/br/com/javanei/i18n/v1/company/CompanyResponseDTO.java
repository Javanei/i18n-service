package br.com.javanei.i18n.v1.company;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("CompanyResult")
public class CompanyResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Company unique ID", required = true)
    private String id;

    @ApiModelProperty(value = "Name", required = true)
    private String name;

    public CompanyResponseDTO() {
    }

    public CompanyResponseDTO(String id, String name) {
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
