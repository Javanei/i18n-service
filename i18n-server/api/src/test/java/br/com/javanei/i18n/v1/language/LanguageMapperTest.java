package br.com.javanei.i18n.v1.language;

import br.com.javanei.i18n.entity.Company;
import br.com.javanei.i18n.entity.Language;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LanguageMapperTest {

    @Test
    public void entityToResponseDTO() {
        Language entity = new Language("1", new Company("2"), "pt_BR", "Brasileiro");

        LanguageResponseDTO dto = LanguageMapper.entityToResponseDTO(entity);

        assertThat(dto.getId()).isEqualTo("1");
        assertThat(dto.getCode()).isEqualTo("pt_BR");
        assertThat(dto.getName()).isEqualTo("Brasileiro");
    }

    @Test
    public void entityFromCreateDTO() {
    }

    @Test
    public void entityFromUpdateDTO() {
    }

    @Test
    public void entitiesToResultDTO() {
    }
}