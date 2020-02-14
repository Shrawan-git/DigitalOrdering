package com.digitalorder.np;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {
    @Rule
    public ActivityTestRule<LoginActivity> testRule = new ActivityTestRule<>(LoginActivity.class);

    private String edname = "biren123";
    private String edpwd = "1234";

    @Test
    public void LoginTest() throws Exception {
        onView(withId(R.id.edname)).perform(typeText(edname));
        closeSoftKeyboard();
        onView(withId(R.id.edpwd)).perform(typeText(edpwd));
        closeSoftKeyboard();
    }
}