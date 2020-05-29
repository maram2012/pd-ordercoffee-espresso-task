package de.deliveryhero.mailordercoffeeshop;

import android.provider.SyncStateContract;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import de.deliveryhero.mailordercoffeeshop.onboarding.OnboardingActivity;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.AllOf.allOf;

@LargeTest
public class EspressoWorkshopTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void closeOnboardingScreen() {
        ViewInteraction closeButton = onView(allOf(withId(R.id.close_button), isDisplayed()));
        closeButton.perform(click());

        ViewInteraction mainTitle = onView(allOf(withId(R.id.title_view), isDisplayed()));
        mainTitle.check(matches(withText("Valori Mail Order Coffeeshop")));
    }


    @Test
    public void OrderingTest() {
       // helpers.closeOnBoardingScreen();

        ViewInteraction espressoPlusIcon = onView(withId(R.id.more_espresso));
        espressoPlusIcon.perform(click());
        espressoPlusIcon.perform(click());

        ViewInteraction espressoShotCounterValue = onView(withId(R.id.espresso_shot_counter));
        espressoShotCounterValue.check(matches(withText("2")));

        //selecting Chocolate
        ViewInteraction chocolateCheckbox = onView(withId(R.id.chocolate));
        chocolateCheckbox.perform(scrollTo(), click());

        //select low fat steamed milk
        ViewInteraction milkSelection = onView(withId(R.id.milk_type));
        milkSelection.perform(scrollTo(), click());
        DataInteraction milkTypeValue = onData(anything()).atPosition(2);


        milkTypeValue.perform(click());

        //select Steamed radio button
        ViewInteraction milkTypeRadioButtons = onView(
                Matchers.allOf(withText("Steamed"), isDisplayed()));
        milkTypeRadioButtons.perform(click());
    }


}
