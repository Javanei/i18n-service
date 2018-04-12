package br.com.javanei.i18n.service;

import br.com.javanei.i18n.dao.CompanyDAO;
import br.com.javanei.i18n.entity.Company;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
public class CompanyService {
    private CompanyDAO companyDAO;

    public CompanyService(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }

    @Transactional(propagation = REQUIRED)
    public Company create(Company entity) {
        entity.setId(UUID.randomUUID().toString());
        entity = companyDAO.save(entity);
        return entity;
    }

    @Transactional(propagation = REQUIRED)
    public Company update(Company entity) {
        Company existing = companyDAO.getOne(entity.getId());
        existing.setName(entity.getName());
        entity = companyDAO.save(existing);
        return entity;
    }

    @Transactional(propagation = REQUIRED)
    public void delete(String id) {
        Company existing = companyDAO.getOne(id);
        companyDAO.delete(existing);
    }

    @Transactional(propagation = NOT_SUPPORTED, readOnly = true)
    public Company findById(String id) {
        return this.getByIdRequired(id);
    }

    @Transactional(propagation = NOT_SUPPORTED, readOnly = true)
    public List<Company> findAll() {
        return companyDAO.findAll();
    }

    private Company getByIdRequired(String id) {
        Optional<Company> o = companyDAO.findById(id);
        if (o.isPresent()) {
            return o.get();
        }
        throw new EntityNotFoundException("Company not found with id [" + id + "]");
    }
}
