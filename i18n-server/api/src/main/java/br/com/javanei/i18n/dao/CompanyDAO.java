package br.com.javanei.i18n.dao;

import br.com.javanei.i18n.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDAO extends JpaRepository<Company, String> {
}
