package br.com.javanei.i18n.service;

import br.com.javanei.i18n.dao.CompanyDAO;
import br.com.javanei.i18n.entity.Company;
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

public class CompanyServiceTest {

    @InjectMocks
    private CompanyService companyService;

    @Mock
    private CompanyDAO companyDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create() {
        //given
        Company entity = new Company();
        entity.setName("Company 1");
        Mockito.when(companyDAO.save(entity)).thenReturn(entity);

        //when
        Company saved = companyService.create(entity);

        //then
        assertThat(saved.getId()).isNotBlank();
    }

    @Test
    public void update() {
        //given
        Company existing = new Company("1", "Existing");
        Company entity = new Company("1", "Company 1");
        Mockito.when(companyDAO.getOne("1")).thenReturn(existing);
        Mockito.when(companyDAO.save(existing)).thenReturn(existing);

        //when
        Company saved = companyService.update(entity);

        //then
        assertThat(saved.getId()).isEqualTo("1");
        assertThat(saved.getName()).isEqualTo("Company 1");
    }

    @Test
    public void findById() {
        //given
        Company existing = new Company("1", "Existing");
        Mockito.when(companyDAO.findById("1")).thenReturn(Optional.of(existing));

        //when
        Company saved = companyService.findById("1");

        //then
        assertThat(saved.getId()).isEqualTo("1");
        assertThat(saved.getName()).isEqualTo("Existing");
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdNotFound() {
        companyService.findById("1");
    }

    @Test
    public void delete() {
        //given
        Company existing = new Company("1", "Existing");
        Mockito.when(companyDAO.getOne("1")).thenReturn(existing);

        //when
        companyService.delete("1");

        //then
        Mockito.verify(companyDAO).delete(existing);
    }

    @Test
    public void findAll() {
        //given
        Company existing = new Company("1", "Existing");
        List<Company> list = new ArrayList<>();
        list.add(existing);
        Mockito.when(companyDAO.findAll()).thenReturn(list);

        //when
        List<Company> result = companyService.findAll();

        //then
        assertThat(result.size()).isEqualTo(1);
    }
}