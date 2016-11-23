package com.carisma.languagetranslator;

import com.carisma.languagetranslator.models.Word;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by aliyuabdullahi on 11/22/16.
 */

public class WordUnitTest {
    Word word;
    @Before
    public void buildUp() {
        word = new Word();
    }

    @Test
    public void wordShouldNotHaveContentOnInitialization() {
        Assert.assertEquals("Word should have null content when initialized", word.getText() == null, true);
    }

    @Test
    public void wordShouldHaveValueWhenSet() {
        word.setText("value");
        Assert.assertEquals("Word contains text if set", word.getText().equals("value"), true);
    }

    @After
    public void tearDown() {
        System.out.println("Done");
    }
}
