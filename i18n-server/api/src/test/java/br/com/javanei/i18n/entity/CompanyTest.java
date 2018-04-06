package br.com.javanei.i18n.entity;

import org.junit.Assert;
import org.junit.Test;

public class CompanyTest {
    @Test
    public void constructorDefault() {
        Company company = new Company();
        Assert.assertNull(company.getId());
        Assert.assertNull(company.getName());
    }

    @Test
    public void constructorById() {
        Company company = new Company("1");
        Assert.assertEquals("1", company.getId());
        Assert.assertNull(company.getName());
    }

    @Test
    public void constructorFull() {
        Company company = new Company("1", "name");
        Assert.assertEquals("1", company.getId());
        Assert.assertEquals("name", company.getName());
    }

    @Test
    public void getterAndSetter() {
        Company company = new Company();
        company.setId("1");
        company.setName("name");
        Assert.assertEquals("1", company.getId());
        Assert.assertEquals("name", company.getName());
    }
}
