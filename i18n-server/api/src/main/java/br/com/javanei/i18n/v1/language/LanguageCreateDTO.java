package br.com.javanei.i18n.v1.language;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("LanguageCreate")
public class LanguageCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Language Code", required = true)
    private String code;

    @ApiModelProperty(value = "Language Name", required = true)
    private String name;

    //TODO: Quando OAuth, deve ser removido para pegar da sessão
    @ApiModelProperty(value = "Company ID", required = true)
    @Deprecated
    private String companyId;

    public LanguageCreateDTO() {
    }

    public LanguageCreateDTO(String code, String name, String companyId) {
        this.code = code;
        this.name = name;
        this.companyId = companyId;
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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "LanguageCreateDTO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", companyId='" + companyId + '\'' +
                '}';
    }
}
