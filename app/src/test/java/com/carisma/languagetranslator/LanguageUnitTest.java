package com.carisma.languagetranslator;

import com.carisma.languagetranslator.models.Language;
import com.carisma.languagetranslator.models.Word;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.realm.RealmList;

/**
 * Created by aliyuabdullahi on 11/22/16.
 */

public class LanguageUnitTest {
    Language language;
    @Before
    public void buildUp() {
      language = new Language();
    }
    
    @Test
    public void languageFormatHasEmptyInitialState() {
        Assert.assertEquals("Language should have initial emtpy format", language.getFormat()==null, true);
    }

    @Test
    public void languageShouldHaveInitialEmptyLanguages() {
        Assert.assertEquals("Language should have initial emtpy language list", language.getWords()==null, true);
    }

    @Test
    public void languageWithFormatShouldNotHaveNullFormat() {
        language.setFormat("en");
        Assert.assertEquals("Language with format should return a format", !language.getFormat().isEmpty(), true);
    }

    @Test
    public void wordsCanBeAddedToLanguage() {
        RealmList<Word> words = new RealmList<>();
        words.add(new Word());
        words.add(new Word());
        language.setWords(words);
        Assert.assertTrue(language.getWords().size() == 2);
    }

    @After
    public void tearDown(){
        System.out.println("Done");
    }
}
