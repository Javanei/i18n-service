package br.com.javanei.i18n.dao;

import br.com.javanei.i18n.entity.Company;
import br.com.javanei.i18n.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectDAO extends JpaRepository<Project, String> {
    List<Project> findByCompany(Company company);

    List<Project> findByCompanyAndParentProject(Company company, Project parentProject);
}
