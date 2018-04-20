package br.com.javanei.i18n.v1.project;

import br.com.javanei.i18n.entity.Company;
import br.com.javanei.i18n.entity.Language;
import br.com.javanei.i18n.entity.Project;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectMapperTest {

    @Test
    public void entityToResponseDTO() {
        Project entity = new Project("1", new Company("2"), "Project 1", new Language("3"), new Project("4"));

        ProjectResponseDTO dto = ProjectMapper.entityToResponseDTO(entity);

        assertThat(dto.getId()).isEqualTo("1");
        assertThat(dto.getDefaultLanguageId()).isEqualTo("3");
        assertThat(dto.getParentProjectId()).isEqualTo("4");
    }

    @Test
    public void entityToResponseDTONullDefaultLanguage() {
        Project entity = new Project("1", new Company("2"), "Project 1", null, new Project("4"));

        ProjectResponseDTO dto = ProjectMapper.entityToResponseDTO(entity);

        assertThat(dto.getId()).isEqualTo("1");
        assertThat(dto.getDefaultLanguageId()).isNull();
        assertThat(dto.getParentProjectId()).isEqualTo("4");
    }

    @Test
    public void entityToResponseDTONullParent() {
        Project entity = new Project("1", new Company("2"), "Project 1", new Language("3"), null);

        ProjectResponseDTO dto = ProjectMapper.entityToResponseDTO(entity);

        assertThat(dto.getId()).isEqualTo("1");
        assertThat(dto.getDefaultLanguageId()).isEqualTo("3");
        assertThat(dto.getParentProjectId()).isNull();
    }

    @Test
    public void entityFromCreateDTO() {
        ProjectCreateDTO dto = new ProjectCreateDTO("Project 1", null, null, "2");

        Project entity = ProjectMapper.entityFromCreateDTO(dto);

        assertThat(entity.getId()).isNull();
        assertThat(entity.getName()).isEqualTo("Project 1");
        assertThat(entity.getDefaultLanguage()).isNull();
        assertThat(entity.getParentProject()).isNull();
        assertThat(entity.getCompany().getId()).isEqualTo("2");
    }

    @Test
    public void entityFromUpdateDTO() {
        ProjectUpdateDTO dto = new ProjectUpdateDTO("Project 1");

        Project entity = ProjectMapper.entityFromUpdateDTO("1", dto);

        assertThat(entity.getId()).isEqualTo("1");
        assertThat(entity.getName()).isEqualTo("Project 1");
        assertThat(entity.getDefaultLanguage()).isNull();
        assertThat(entity.getParentProject()).isNull();
        assertThat(entity.getCompany()).isNull();
    }

    @Test
    public void entitiesToResultDTO() {
        List<Project> list = new LinkedList<>();
        Project entity = new Project("1", new Company("2"), "Project 1", new Language("3"), null);
        list.add(entity);
        entity = new Project("2", new Company("3"), "Project 2", new Language("4"), new Project("1"));
        list.add(entity);

        List<ProjectResponseDTO> result = ProjectMapper.entitiesToResultDTO(list);

        assertThat(result.size()).isEqualTo(2);
    }
}