package com.digitalorder.np;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


    @RunWith(AndroidJUnit4.class)
    @LargeTest
    public class OrderTest {
        @Rule
        public ActivityTestRule<Onclickfood> testRule = new ActivityTestRule<>(Onclickfood.class);


        @Test
        public void OrderTest() throws Exception {
            onView(withId(R.id.orderForm)).perform(click());

        }
    }


