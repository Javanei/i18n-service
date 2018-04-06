package br.com.javanei.i18n.entity;

import org.junit.Assert;
import org.junit.Test;

public class LanguageTest {
    @Test
    public void constructorDefault() {
        Language language = new Language();
        Assert.assertNull(language.getId());
        Assert.assertNull(language.getCode());
        Assert.assertNull(language.getName());
    }

    @Test
    public void constructorById() {
        Language language = new Language("1");
        Assert.assertEquals("1", language.getId());
        Assert.assertNull(language.getCode());
        Assert.assertNull(language.getName());
    }

    @Test
    public void constructorNormal() {
        Language language = new Language("code", "name");
        Assert.assertNull(language.getId());
        Assert.assertEquals("code", language.getCode());
        Assert.assertEquals("name", language.getName());
    }

    @Test
    public void constructorFull() {
        Language language = new Language("1", "code", "name");
        Assert.assertEquals("1", language.getId());
        Assert.assertEquals("code", language.getCode());
        Assert.assertEquals("name", language.getName());
    }

    @Test
    public void getterAndSetter() {
        Language language = new Language();
        language.setId("1");
        language.setCode("code");
        language.setName("name");
        Assert.assertEquals("1", language.getId());
        Assert.assertEquals("code", language.getCode());
        Assert.assertEquals("name", language.getName());
    }
}