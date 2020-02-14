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
    public class RegisterTest {

        @Rule
        public ActivityTestRule<RegisterActivity> testRule = new ActivityTestRule<>(RegisterActivity.class);

        private String etfullname = "Utsav shrestha";
        private String etname ="utsav123";
        private  String etemail = "shresthautsav96@gmail.com";
        private String etphone = "9841562857";
        private String etpwd = "password";
        private String etcpwd = "password";

        @Test
        public void RegisterTest() throws Exception {
            onView(withId(R.id.etfullname)).perform(typeText(etfullname));
            closeSoftKeyboard();
            onView(withId(R.id.etname)).perform(typeText(etname));
            closeSoftKeyboard();
            onView(withId(R.id.etemail)).perform(typeText(etemail));
            closeSoftKeyboard();
            onView(withId(R.id.etphone)).perform(typeText(etphone));
            closeSoftKeyboard();
            onView(withId(R.id.etpwd)).perform(typeText(etpwd));
            closeSoftKeyboard();
            onView(withId(R.id.etcpwd)).perform(typeText(etcpwd));
            closeSoftKeyboard();
            onView(withId(R.id.btn)).perform(click());

    }
}
