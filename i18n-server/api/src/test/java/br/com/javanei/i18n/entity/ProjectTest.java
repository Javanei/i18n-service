package br.com.javanei.i18n.entity;

import org.junit.Assert;
import org.junit.Test;

public class ProjectTest {
    @Test
    public void constructorDefault() {
        Project project = new Project();
        Assert.assertNull(project.getId());
        Assert.assertNull(project.getName());
        Assert.assertNull(project.getCompanyId());
        Assert.assertNull(project.getDefaultLanguageId());
        Assert.assertNull(project.getParentProjectId());
    }

    @Test
    public void constructorById() {
        Project project = new Project("1");
        Assert.assertEquals("1", project.getId());
        Assert.assertNull(project.getName());
        Assert.assertNull(project.getCompanyId());
        Assert.assertNull(project.getDefaultLanguageId());
        Assert.assertNull(project.getParentProjectId());
    }

    @Test
    public void constructorNormal() {
        Project project = new Project("name", "company", "language");
        Assert.assertNull(project.getId());
        Assert.assertEquals("name", project.getName());
        Assert.assertEquals("company", project.getCompanyId());
        Assert.assertEquals("language", project.getDefaultLanguageId());
        Assert.assertNull(project.getParentProjectId());
    }

    @Test
    public void constructorFull() {
        Project project = new Project("1", "name", "company", "language");
        Assert.assertEquals("1", project.getId());
        Assert.assertEquals("name", project.getName());
        Assert.assertEquals("company", project.getCompanyId());
        Assert.assertEquals("language", project.getDefaultLanguageId());
        Assert.assertNull(project.getParentProjectId());
    }

    @Test
    public void getterAndSetter() {
        Project project = new Project();
        project.setId("1");
        project.setName("name");
        project.setCompanyId("company");
        project.setDefaultLanguageId("language");
        project.setParentProjectId("parent");
        Assert.assertEquals("1", project.getId());
        Assert.assertEquals("name", project.getName());
        Assert.assertEquals("company", project.getCompanyId());
        Assert.assertEquals("language", project.getDefaultLanguageId());
        Assert.assertEquals("parent", project.getParentProjectId());
    }
}