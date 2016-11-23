package com.carisma.languagetranslator;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.carisma.languagetranslator.models.Word;
import com.carisma.languagetranslator.utilities.LanguageFetcher;
import com.carisma.languagetranslator.utilities.LanguageProcessor;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.Espresso.onView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by aliyuabdullahi on 11/22/16.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentationTest {
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void allViewsAvailable() {
        onView(withId(R.id.spinner_destination)).check(matches(isDisplayed()));
        onView(withId(R.id.spinner_source)).check(matches(isDisplayed()));
        onView(withId(R.id.word_input_edit_text)).check(matches(isDisplayed()));
        onView(withId(R.id.toggle_language)).check(matches(isDisplayed()));
        onView(withId(R.id.convert_language_button)).check(matches(isDisplayed()));
    }

    @Test
    public void buttonShouldPerformClickAction() {
        onView(withId(R.id.convert_language_button)).perform(click());
    }

    @Test
    public void testActivityLanguageProcessor() {
        LanguageProcessor processor = new LanguageProcessor(activityActivityTestRule.getActivity().getApplicationContext());
        processor.setLanguages(new String[]{"English", "French", "Arabic"});
        processor.setFormats(new String[]{"en", "fr", "ar"});
        Assert.assertEquals("Processor should return correct language format", processor.extractFormat("English").equals("en"), true);
        Assert.assertEquals("Processor should return correct language format", processor.extractFormat("French").equals("fr"), true);
        Assert.assertEquals("Processor should return correct language format", processor.extractFormat("Arabic").equals("ar"), true);
    }

    @Test(timeout = 200)
    public void languageFetcherCanMakeApiCall() {
        LanguageFetcher fetcher = new LanguageFetcher(activityActivityTestRule.getActivity().getApplicationContext());
        fetcher.getWord("how are you", "en", "fr", new LanguageFetcher.OnWordObtainedListener() {
            @Override
            public void onWordObtained(Word word) {
                Assert.assertEquals("Word contains object", word != null, true);
                Assert.assertTrue(word.getText().length()>0);
                Assert.assertTrue(!word.getText().equals("Error"));
            }
        });
    }
}
