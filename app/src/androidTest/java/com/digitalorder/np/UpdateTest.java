package com.digitalorder.np;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.android21buttons.fragmenttestrule.FragmentTestRule;
import com.digitalorder.np.ui.profile.ProfileFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class UpdateTest {

    @Rule
    public FragmentTestRule<?, ProfileFragment> fragmentTestRule =
            FragmentTestRule.create(ProfileFragment.class);

    private String tvFullname = "Utsav shrestha";
    private String tvName ="utsav123";
    private  String tvEmail = "shresthautsav96@gmail.com";
    private String tvGender = "Male";
    private String tvPhone = "9841562857";

    @Test
    public void UpdateTest() throws Exception {
        onView(withId(R.id.tvFullname)).perform(typeText(tvFullname), closeSoftKeyboard());
        onView(withId(R.id.tvName)).perform(typeText(tvName), closeSoftKeyboard());
        onView(withId(R.id.tvEmail)).perform(typeText(tvEmail), closeSoftKeyboard());
        onView(withId(R.id.tvGender)).perform(typeText(tvGender), closeSoftKeyboard());
        onView(withId(R.id.tvPhone)).perform(typeText(tvPhone), closeSoftKeyboard());
        onView(withId(R.id.update)).perform(click());

    }
}
