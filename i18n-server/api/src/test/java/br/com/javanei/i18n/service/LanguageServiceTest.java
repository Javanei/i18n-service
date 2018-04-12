package br.com.javanei.i18n.service;

import br.com.javanei.i18n.dao.LanguageDAO;
import br.com.javanei.i18n.entity.Company;
import br.com.javanei.i18n.entity.Language;
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

public class LanguageServiceTest {

    @InjectMocks
    private LanguageService languageService;

    @Mock
    private LanguageDAO languageDAO;

    @Mock
    private CompanyService companyService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create() {
        //given
        Language entity = new Language();
        entity.setCode("pt");
        entity.setName("Portugues");
        entity.setCompany(getCompany());
        Mockito.when(languageDAO.save(entity)).thenReturn(entity);
        Mockito.when(companyService.findById("1")).thenReturn(getCompany());

        //when
        Language saved = languageService.create(entity);

        //then
        assertThat(saved.getId()).isNotBlank();
    }

    @Test
    public void update() {
        //given
        Language existing = new Language("1", getCompany(), "pt_BR", "Portugues");
        Language entity = new Language("1", getCompany(), "pt_BR", "Portugues Brasileiro");
        Mockito.when(languageDAO.getOne("1")).thenReturn(existing);
        Mockito.when(languageDAO.save(existing)).thenReturn(existing);

        //when
        Language saved = languageService.update(entity);

        //then
        assertThat(saved.getId()).isEqualTo("1");
        assertThat(saved.getName()).isEqualTo("Portugues Brasileiro");
    }

    @Test
    public void findById() {
        //given
        Language existing = new Language("1", getCompany(), "ex", "Existing");
        Mockito.when(languageDAO.findById("1")).thenReturn(Optional.of(existing));

        //when
        Language saved = languageService.findById("1");

        //then
        assertThat(saved.getId()).isEqualTo("1");
        assertThat(saved.getName()).isEqualTo("Existing");
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdNotFound() {
        languageService.findById("1");
    }

    @Test
    public void delete() {
        //given
        Language existing = new Language("1", getCompany(), "ex", "Existing");
        Mockito.when(languageDAO.getOne("1")).thenReturn(existing);

        //when
        languageService.delete("1");

        //then
        Mockito.verify(languageDAO).delete(existing);
    }

    @Test
    public void findAll() {
        //given
        Language existing = new Language("1", getCompany(), "ex", "Existing");
        List<Language> list = new ArrayList<>();
        list.add(existing);
        Mockito.when(languageDAO.findAll()).thenReturn(list);

        //when
        List<Language> result = languageService.findAll();

        //then
        assertThat(result.size()).isEqualTo(1);
    }

    private Company getCompany() {
        return new Company("1", "Company");
    }
}
