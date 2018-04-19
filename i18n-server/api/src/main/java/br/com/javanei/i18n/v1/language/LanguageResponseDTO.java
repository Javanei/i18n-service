package br.com.javanei.i18n.v1.language;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("LanguageResponse")
public class LanguageResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Language unique ID", required = true)
    private String id;

    @ApiModelProperty(value = "Language Code", required = true)
    private String code;

    @ApiModelProperty(value = "Language Name", required = true)
    private String name;

    public LanguageResponseDTO() {
    }

    public LanguageResponseDTO(String id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "LanguageResponseDTO{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
