package br.com.javanei.i18n.v1.language;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("LanguageUpdate")
public class LanguageUpdateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Language Name", required = true)
    private String name;

    public LanguageUpdateDTO() {
    }

    public LanguageUpdateDTO(String name) {
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
        return "LanguageUpdateDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
