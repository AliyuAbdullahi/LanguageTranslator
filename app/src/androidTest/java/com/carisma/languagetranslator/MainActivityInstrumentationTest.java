package com.carisma.languagetranslator;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.Espresso.onView;

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
        onView(withId(R.id.spinner_result)).check(matches(isDisplayed()));
        onView(withId(R.id.spinner_source)).check(matches(isDisplayed()));
        onView(withId(R.id.word_input)).check(matches(isDisplayed()));
        onView(withId(R.id.toggle_language)).check(matches(isDisplayed()));
        onView(withId(R.id.convert_language)).check(matches(isDisplayed()));
    }

}
