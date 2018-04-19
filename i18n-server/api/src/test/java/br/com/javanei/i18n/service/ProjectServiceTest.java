package br.com.javanei.i18n.service;

import br.com.javanei.i18n.dao.ProjectDAO;
import br.com.javanei.i18n.entity.Company;
import br.com.javanei.i18n.entity.Language;
import br.com.javanei.i18n.entity.Project;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectServiceTest {

    @InjectMocks
    private ProjectService projectService;

    @Mock
    private ProjectDAO projectDAO;

    @Mock
    private CompanyService companyService;

    @Mock
    private LanguageService languageService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create() {
        //given
        Company company = getCompany();
        Project entity = new Project();
        entity.setName("Project 1");
        entity.setCompany(company);
        Mockito.when(projectDAO.save(entity)).thenReturn(entity);
        Mockito.when(companyService.findById("1")).thenReturn(company);

        //when
        Project saved = projectService.create(entity);

        //then
        assertThat(saved.getId()).isNotBlank();
        assertThat(saved.getName()).isEqualTo("Project 1");
    }

    @Test
    public void createWithDefaultLanguage() {
        //given
        Company company = getCompany();
        Language language = getLanguage("1");
        Project entity = new Project();
        entity.setName("Project 1");
        entity.setCompany(company);
        entity.setDefaultLanguage(language);
        Mockito.when(projectDAO.save(entity)).thenReturn(entity);
        Mockito.when(companyService.findById("1")).thenReturn(company);
        Mockito.when(languageService.findById("1")).thenReturn(language);

        //when
        Project saved = projectService.create(entity);

        //then
        assertThat(saved.getId()).isNotBlank();
        assertThat(saved.getName()).isEqualTo("Project 1");
        assertThat(saved.getDefaultLanguage()).isNotNull();
    }

    @Test
    public void createWithParentProject() {
        //given
        Company company = getCompany();
        Optional<Project> parent = Optional.of(getProject("2"));
        Project entity = new Project();
        entity.setName("Project 1");
        entity.setCompany(company);
        entity.setParentProject(parent.get());
        Mockito.when(projectDAO.save(entity)).thenReturn(entity);
        Mockito.when(companyService.findById("1")).thenReturn(company);
        Mockito.when(projectDAO.findById("2")).thenReturn(parent);

        //when
        Project saved = projectService.create(entity);

        //then
        assertThat(saved.getId()).isNotBlank();
        assertThat(saved.getName()).isEqualTo("Project 1");
        assertThat(saved.getParentProject()).isEqualTo(parent.get());
    }

    @Test
    public void update() {
        //given
        Project existing = new Project("1", getCompany(), "Project 1", null, null);
        Project entity = new Project("1", getCompany(), "Project 2", null, null);
        Mockito.when(projectDAO.getOne("1")).thenReturn(existing);
        Mockito.when(projectDAO.save(existing)).thenReturn(existing);

        //when
        Project saved = projectService.update(entity);

        //then
        assertThat(saved.getId()).isEqualTo("1");
        assertThat(saved.getName()).isEqualTo("Project 2");
    }

    @Test
    public void findById() {
        //given
        Project existing = new Project("1", getCompany(), "Project 1", null, null);
        Mockito.when(projectDAO.findById("1")).thenReturn(Optional.of(existing));

        //when
        Project saved = projectService.findById("1");

        //then
        assertThat(saved.getId()).isEqualTo("1");
        assertThat(saved.getName()).isEqualTo("Project 1");
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdNotFound() {
        projectService.findById("1");
    }

    @Test
    public void delete() {
        //given
        Project existing = new Project("1", getCompany(), "Project 1", null, null);
        Mockito.when(projectDAO.getOne("1")).thenReturn(existing);

        //when
        projectService.delete("1");

        //then
        Mockito.verify(projectDAO).delete(existing);
    }

    @Test
    public void findAll() {
        //given
        Project existing = new Project("1", getCompany(), "Project 1", null, null);
        List<Project> list = new ArrayList<>();
        list.add(existing);
        Mockito.when(projectDAO.findAll()).thenReturn(list);

        //when
        List<Project> result = projectService.findAll();

        //then
        assertThat(result.size()).isEqualTo(1);
    }

    private Company getCompany() {
        return new Company("1", "Company");
    }

    private Project getProject(String id) {
        Project p = new Project(id);
        if (id != null) {
            p.setName("Project: " + id);
        }
        return p;
    }

    private Language getLanguage(String id) {
        return new Language(id);
    }
}