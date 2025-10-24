package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import android.widget.ListView;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowActivityTest {

    // Launch MainActivity fresh before each test
    @Rule
    public ActivityScenarioRule<MainActivity> scenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    // Add a city to the MainActivity list so we can tap itt a us
    private void addCity(String name) {
        // Open the "add city" UI
        onView(withId(R.id.button_add)).perform(click());

        // Type into the EditText
        onView(withId(R.id.editText_name))
                .perform(typeText(name), closeSoftKeyboard());

        // Confirm
        onView(withId(R.id.button_confirm)).perform(click());
    }

    // Check whether tapping a list item actually opens ShowActivity.
    @Test
    public void testActivitySwitch() {
        // Create one city entry in the list
        addCity("Calgary");

        // Click the first row in the ListView
        onData(is(instanceOf(String.class)))
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // ShowActivity is now on screen
        // We know this because text_selected_city is part of activity_show.xml
        onView(withId(R.id.text_selected_city))
                .check(matches(isDisplayed()));
    }

    // Test that the city name we clicked in MainActivity is exactly what ShowActivity displays.
    @Test
    public void testCityName() {
        // Arrange
        String expectedCity = "Toronto";
        addCity(expectedCity);

        // Click the first row in the list
        onData(is(instanceOf(String.class)))
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // ShowActivity displays "Toronto"
        onView(withId(R.id.text_selected_city))
                .check(matches(withText(expectedCity)));
    }


    // Test that the Back button actually returns us to MainActivity (i.e., we can see the ListView again).
    @Test
    public void testBackButton() {

        // Add a city to the list in MainActivity
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name))
                .perform(typeText("Edmonton"), closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());

        // Tap the first item -> this should open ShowActivity
        onData(is(instanceOf(String.class)))
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // We are now in ShowActivity. Check:
        // The selected city TextView is shown
        onView(withId(R.id.text_selected_city))
                .check(matches(isDisplayed()));

        // Back button added is visible on this screen
        onView(withId(R.id.button_back))
                .check(matches(isDisplayed()));

        // Simulate going back (equivalent to pressing that button in terms of navigation result)
        pressBack();

        // We ARE back in MainActivity: the ListView is visible again
        onView(withId(R.id.city_list))
                .check(matches(isDisplayed()));
    }
}

