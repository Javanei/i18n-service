package br.com.javanei.i18n.service;

import br.com.javanei.i18n.dao.ProjectDAO;
import br.com.javanei.i18n.entity.Project;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
public class ProjectService {
    private ProjectDAO projectDAO;
    private CompanyService companyService;
    private LanguageService languageService;

    public ProjectService(ProjectDAO projectDAO, CompanyService companyService, LanguageService languageService) {
        this.projectDAO = projectDAO;
        this.companyService = companyService;
        this.languageService = languageService;
    }

    @Transactional(propagation = REQUIRED)
    public Project create(Project entity) {
        entity.setCompany(companyService.findById(entity.getCompany().getId()));
        if (entity.getDefaultLanguage() != null) {
            entity.setDefaultLanguage(languageService.findById(entity.getDefaultLanguage().getId()));
        }
        if (entity.getParentProject() != null) {
            entity.setParentProject(getByIdRequired(entity.getParentProject().getId()));
        }
        entity.setId(UUID.randomUUID().toString());
        entity = projectDAO.save(entity);
        return entity;
    }

    @Transactional(propagation = REQUIRED)
    public Project update(Project entity) {
        Project existing = projectDAO.getOne(entity.getId());
        existing.setName(entity.getName());
        entity = projectDAO.save(existing);
        return entity;
    }

    @Transactional(propagation = REQUIRED)
    public void delete(String id) {
        Project existing = projectDAO.getOne(id);
        projectDAO.delete(existing);
    }

    @Transactional(propagation = NOT_SUPPORTED, readOnly = true)
    public Project findById(String id) {
        return this.getByIdRequired(id);
    }

    @Transactional(propagation = NOT_SUPPORTED, readOnly = true)
    public List<Project> findAll() {
        return projectDAO.findAll();
    }

    private Project getByIdRequired(String id) {
        Optional<Project> o = projectDAO.findById(id);
        if (o.isPresent()) {
            return o.get();
        }
        throw new EntityNotFoundException("Project not found with id [" + id + "]");
    }
}
