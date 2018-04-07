package br.com.javanei.i18n.dao;

import br.com.javanei.i18n.entity.Company;
import br.com.javanei.i18n.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageDAO extends JpaRepository<Language, String> {
    List<Language> findByCompany(Company company);

    Language findByCompanyAndCode(Company company, String code);

    Language findByCompanyAndName(Company company, String name);
}
