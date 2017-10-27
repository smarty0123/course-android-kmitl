package kmitl.lab08.nattapon58070036.espresso;


import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    // Convenience helper
    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }


    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    //โดยไม่กรอก Name และ Age กดปุ่ม ADDED จะต้องเจอ Please Enter user info
    @Test
    public void testCase1() {

        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(android.R.id.message)).check(matches(withText("Please Enter user info")));

        onView(withId(android.R.id.button1)).perform(click());

    }

    //โดยไม่กรอก Name และ Age=20 กดปุ่ม ADDED จะต้องเจอ Please Enter user info
    @Test
    public void testCase2() {
        onView(withId(R.id.editTextAge)).perform(replaceText("20"));

        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(android.R.id.message)).check(matches(withText("Please Enter user info")));

        onView(withId(android.R.id.button1)).perform(click());
    }

    //ยังไม่มีการเพิ่ม UserInfo และกด GO TO LIST จะเจอ Not Found
    @Test
    public void testCase3() {
        onView(withId(R.id.buttonGotoList)).perform(click());

        onView(withId(R.id.textNotFound)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    //โดยไม่กรอก Age และ Name=Ying กดปุ่ม ADDED จะต้องเจอ Please Enter user info
    @Test
    public void testCase4() {

        onView(withId(R.id.editTextName)).perform(replaceText("Ying"));

        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(android.R.id.message)).check(matches(withText("Please Enter user info")));

        onView(withId(android.R.id.button1)).perform(click());
    }

    //โดยกรอก Name=Ying และ Age=20 กดปุ่ม ADDED และกด GO TO LIST จะต้องเจอ Ying อายุ 20 เป็นตัวแรก
    @Test
    public void testCase5() {

        onView(withId(R.id.editTextName)).perform(replaceText("Ying"), closeSoftKeyboard());

        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());

        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.buttonGotoList)).perform(click());

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(0, R.id.textName))
                .check(matches(withText("Ying")));

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(0, R.id.textAge))
                .check(matches(withText("20")));
    }

    //โดยกรอก Name=Ladarat และ Age=20 กดปุ่ม ADDED และกด GO TO LIST จะต้องเจอ Ladarat อายุ 20 ใน ListView ลำดับที่ 2
    @Test
    public void testCase6() {
        onView(withId(R.id.editTextName)).perform(click());

        onView(withId(R.id.editTextName)).perform(replaceText("Ladarat"), closeSoftKeyboard());

        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());

        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.buttonGotoList)).perform(click());

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(1, R.id.textName))
                .check(matches(withText("Ladarat")));

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(1, R.id.textAge))
                .check(matches(withText("20")));


    }

    //โดยกรอก Name=Somkait และ Age=80 กดปุ่ม ADDED และกด GO TO LIST จะต้องเจอ Somkait อายุ 80 ใน ListView ลำดับที่ 3
    @Test
    public void testCase7() {
        onView(withId(R.id.editTextName)).perform(click());

        onView(withId(R.id.editTextName)).perform(replaceText("Somkait"), closeSoftKeyboard());

        onView(withId(R.id.editTextAge)).perform(replaceText("80"), closeSoftKeyboard());

        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.buttonGotoList)).perform(click());

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(2, R.id.textName))
                .check(matches(withText("Somkait")));

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(2, R.id.textAge))
                .check(matches(withText("80")));
    }

    //โดยกรอก Name=Prayoch และ Age=60 กดปุ่ม ADDED และกด GO TO LIST จะต้องเจอ Prayoch อายุ 60 ใน ListView ลำดับที่ 4
    @Test
    public void testCase8() {
        onView(withId(R.id.editTextName)).perform(click());

        onView(withId(R.id.editTextName)).perform(replaceText("Prayoch"), closeSoftKeyboard());

        onView(withId(R.id.editTextAge)).perform(replaceText("60"), closeSoftKeyboard());

        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.buttonGotoList)).perform(click());

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(3, R.id.textName))
                .check(matches(withText("Prayoch")));

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(3, R.id.textAge))
                .check(matches(withText("60")));

    }

    //โดยกรอก Name=Prayoch และ Age=50 กดปุ่ม ADDED และกด GO TO LIST จะต้องเจอ Prayoch อายุ 50 ใน ListView ลำดับที่ 5
    @Test
    public void testCase9() {
        onView(withId(R.id.editTextName)).perform(click());

        onView(withId(R.id.editTextName)).perform(replaceText("Prayoch"), closeSoftKeyboard());

        onView(withId(R.id.editTextAge)).perform(replaceText("50"), closeSoftKeyboard());

        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.buttonGotoList)).perform(click());

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(4, R.id.textName))
                .check(matches(withText("Prayoch")));

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(4, R.id.textAge))
                .check(matches(withText("50")));

    }

    //โดยกดปุ่มGO TO LISTแล้วกดปุ่ม CLEAR LIST จะเจอ Not Found
    @Test
    public void testCase10() {

        onView(withId(R.id.buttonGotoList)).perform(click());

        onView(withId(R.id.btnClear)).perform(click());

        onView(withId(R.id.textNotFound)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }


}
