package br.com.javanei.i18n.v1.project;

import br.com.javanei.i18n.entity.Language;
import br.com.javanei.i18n.entity.Project;
import org.modelmapper.ModelMapper;

import java.util.LinkedList;
import java.util.List;

public class ProjectMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    private ProjectMapper() {
    }

    public static ProjectResponseDTO entityToResponseDTO(Project entity) {
        ProjectResponseDTO result = modelMapper.map(entity, ProjectResponseDTO.class);
        if (entity.getParentProject() != null) {
            result.setParentProjectId(entity.getParentProject().getId());
        }
        if (entity.getDefaultLanguage() != null) {
            result.setDefaultLanguageId(entity.getDefaultLanguage().getId());
        }
        return result;
    }

    public static Project entityFromCreateDTO(ProjectCreateDTO dto) {
        Project result = modelMapper.map(dto, Project.class);
        //TODO: O ModelMapper tá usando o getCompany().getId() para set o ID!!!!
        result.setId(null);
        if (dto.getDefaultLanguageId() != null) {
            result.setDefaultLanguage(new Language(dto.getDefaultLanguageId()));
        }
        if (dto.getParentProjectId() != null) {
            result.setParentProject(new Project(dto.getParentProjectId()));
        }
        return result;
    }

    public static Project entityFromUpdateDTO(String id, ProjectUpdateDTO dto) {
        Project entity = modelMapper.map(dto, Project.class);
        entity.setId(id);
        return entity;
    }

    public static List<ProjectResponseDTO> entitiesToResultDTO(List<Project> entities) {
        List<ProjectResponseDTO> result = new LinkedList<>();
        for (Project p : entities) {
            result.add(entityToResponseDTO(p));
        }
        return result;
    }
}
