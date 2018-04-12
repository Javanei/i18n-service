package br.com.javanei.i18n.service;

import br.com.javanei.i18n.dao.LanguageDAO;
import br.com.javanei.i18n.entity.Language;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
public class LanguageService {
    private LanguageDAO languageDAO;
    private CompanyService companyService;

    public LanguageService(LanguageDAO languageDAO, CompanyService companyService) {
        this.languageDAO = languageDAO;
        this.companyService = companyService;
    }

    @Transactional(propagation = REQUIRED)
    public Language create(Language entity) {
        entity.setCompany(companyService.findById(entity.getCompany().getId()));
        entity.setId(UUID.randomUUID().toString());
        entity = languageDAO.save(entity);
        return entity;
    }

    @Transactional(propagation = REQUIRED)
    public Language update(Language entity) {
        Language existing = languageDAO.getOne(entity.getId());
        existing.setName(entity.getName());
        entity = languageDAO.save(existing);
        return entity;
    }

    @Transactional(propagation = REQUIRED)
    public void delete(String id) {
        Language existing = languageDAO.getOne(id);
        languageDAO.delete(existing);
    }

    @Transactional(propagation = NOT_SUPPORTED, readOnly = true)
    public Language findById(String id) {
        return this.getByIdRequired(id);
    }

    @Transactional(propagation = NOT_SUPPORTED, readOnly = true)
    public List<Language> findAll() {
        return languageDAO.findAll();
    }

    private Language getByIdRequired(String id) {
        Optional<Language> o = languageDAO.findById(id);
        if (o.isPresent()) {
            return o.get();
        }
        throw new EntityNotFoundException("Language not found with id [" + id + "]");
    }
}
