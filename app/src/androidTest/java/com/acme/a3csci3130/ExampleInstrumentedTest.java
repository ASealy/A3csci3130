package com.acme.a3csci3130;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.deps.guava.primitives.Booleans;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsAnything.anything;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void createBusinessTest() throws Exception{

        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.name)).perform(clearText(),typeText("My Business"),closeSoftKeyboard());
        onView(withId(R.id.businessNumber)).perform(clearText(),typeText("123456789"),closeSoftKeyboard());
        onView(withId(R.id.primaryBusiness)).perform(clearText(),typeText("Fisher"),closeSoftKeyboard());
        onView(withId(R.id.address)).perform(clearText(),typeText("123 Fake Street"),closeSoftKeyboard());
        onView(withId(R.id.province)).perform(clearText(),typeText("NS"),closeSoftKeyboard());
        onView(withId(R.id.submitButton)).perform(click());
        Assert.assertEquals("My Business",MainActivity.firebaseAdapter.getItem(0).name);
        Assert.assertEquals("123456789",MainActivity.firebaseAdapter.getItem(0).businessNumber);
        Assert.assertEquals("Fisher",MainActivity.firebaseAdapter.getItem(0).primaryBusiness);
        Assert.assertEquals("123 Fake Street",MainActivity.firebaseAdapter.getItem(0).address);
        Assert.assertEquals("NS",MainActivity.firebaseAdapter.getItem(0).province);

    }

    @Test
    public void updateBusinessTest() throws Exception{
        Thread.sleep(3000);
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.name)).perform(clearText(),typeText("My Business"),closeSoftKeyboard());
        onView(withId(R.id.businessNumber)).perform(clearText(),typeText("123456789"),closeSoftKeyboard());
        onView(withId(R.id.primaryBusiness)).perform(clearText(),typeText("Fisher"),closeSoftKeyboard());
        onView(withId(R.id.address)).perform(clearText(),typeText("123 Fake Street"),closeSoftKeyboard());
        onView(withId(R.id.province)).perform(clearText(),typeText("NS"),closeSoftKeyboard());
        onView(withId(R.id.updateButton)).perform(click());
        Assert.assertEquals("My Business",MainActivity.firebaseAdapter.getItem(0).name);
        Assert.assertEquals("123456789",MainActivity.firebaseAdapter.getItem(0).businessNumber);
        Assert.assertEquals("Fisher",MainActivity.firebaseAdapter.getItem(0).primaryBusiness);
        Assert.assertEquals("123 Fake Street",MainActivity.firebaseAdapter.getItem(0).address);
        Assert.assertEquals("NS",MainActivity.firebaseAdapter.getItem(0).province);

    }

    @Test
    public void deleteBusinessTest() throws Exception{
        Thread.sleep(5000);
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());

        String before;
        if(MainActivity.firebaseAdapter.isEmpty()) before="emptyBefore";
        else before=MainActivity.firebaseAdapter.getItem(0).uid;

        onView(withId(R.id.deleteButton)).perform(click());

        String after;
        if(MainActivity.firebaseAdapter.isEmpty()) after="emptyAfter";
        else after=MainActivity.firebaseAdapter.getItem(0).uid;

        Assert.assertNotEquals(before,after);

    }

}