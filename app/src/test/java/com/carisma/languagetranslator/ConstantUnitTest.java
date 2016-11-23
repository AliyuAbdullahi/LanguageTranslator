package com.carisma.languagetranslator;

import com.carisma.languagetranslator.constants.Constants;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by aliyuabdullahi on 11/23/16.
 */

public class ConstantUnitTest {
    @Test
    public void constantIsASingleton() {
        String mockUrl = "https://translate.yandex.net/api/v1.5/tr.json/translate";
        Assert.assertTrue(Constants.baseTranslateUrl.equals(mockUrl));
    }
}
