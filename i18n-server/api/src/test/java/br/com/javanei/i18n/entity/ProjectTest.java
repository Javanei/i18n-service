package br.com.javanei.i18n.entity;

import org.junit.Assert;
import org.junit.Test;

public class ProjectTest {
    @Test
    public void constructorDefault() {
        Project project = new Project();
        Assert.assertNull(project.getId());
        Assert.assertNull(project.getCompany());
        Assert.assertNull(project.getName());
        Assert.assertNull(project.getDefaultLanguage());
        Assert.assertNull(project.getParentProject());
    }

    @Test
    public void constructorById() {
        Project project = new Project("1");
        Assert.assertEquals("1", project.getId());
        Assert.assertNull(project.getCompany());
        Assert.assertNull(project.getName());
        Assert.assertNull(project.getDefaultLanguage());
        Assert.assertNull(project.getParentProject());
    }

    @Test
    public void constructorNormal() {
        Project project = new Project("name", new Language("1"));
        Assert.assertNull(project.getId());
        Assert.assertNull(project.getCompany());
        Assert.assertEquals("name", project.getName());
        Assert.assertNotNull(project.getDefaultLanguage());
        Assert.assertNull(project.getParentProject());
    }

    @Test
    public void constructorFull() {
        Project project = new Project("1", new Company("1"), "name", new Language("1"), new Project("2"));
        Assert.assertEquals("1", project.getId());
        Assert.assertEquals("name", project.getName());
        Assert.assertNotNull(project.getCompany());
        Assert.assertNotNull(project.getDefaultLanguage());
        Assert.assertNotNull(project.getParentProject());
    }

    @Test
    public void getterAndSetter() {
        Project project = new Project();
        project.setId("1");
        project.setName("name");
        project.setCompany(new Company("1"));
        project.setDefaultLanguage(new Language("1"));
        project.setParentProject(new Project("2"));
        Assert.assertEquals("1", project.getId());
        Assert.assertEquals("name", project.getName());
        Assert.assertNotNull(project.getCompany());
        Assert.assertNotNull(project.getDefaultLanguage());
        Assert.assertNotNull(project.getParentProject());
    }
}