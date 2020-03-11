package es.ulpgc.eite.da.basicquizlab;


import android.content.pm.ActivityInfo;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.ulpgc.eite.da.basicquizlab.question.QuestionActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LandscapeQuizTest {

  @Rule
  public ActivityTestRule<QuestionActivity> mActivityTestRule =
      new ActivityTestRule<>(QuestionActivity.class);

  @Test
  public void landscapeQuestionTest() {

    // GIVEN

    ViewInteraction textView = onView(withId(R.id.questionText));
    textView.check(matches(withText("Question #1: True")));

    ViewInteraction textView2 = onView(withId(R.id.replyText));
    textView2.check(matches(withText("???")));

    // WHEN

    ViewInteraction appCompatButton = onView(withId(R.id.trueButton));
    appCompatButton.perform(click());

    // THEN & GIVEN

    ViewInteraction textView3 = onView(withId(R.id.questionText));
    textView3.check(matches(withText("Question #1: True")));

    ViewInteraction textView4 = onView(withId(R.id.replyText));
    textView4.check(matches(withText("Correct")));

    // ---------------------

    // WHEN

    mActivityTestRule.getActivity()
        .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

    // THEN & GIVEN

    ViewInteraction textView5 = onView(withId(R.id.questionText));
    textView5.check(matches(withText("Question #1: True")));

    ViewInteraction textView6 = onView(withId(R.id.replyText));
    textView6.check(matches(withText("Correct")));

    // ---------------------

    // WHEN

    ViewInteraction appCompatButton2 = onView(withId(R.id.nextButton));
    appCompatButton2.perform(click());

    // THEN & GIVEN

    ViewInteraction textView7 = onView(withId(R.id.questionText));
    textView7.check(matches(withText("Question #2: False")));

    ViewInteraction textView8 = onView(withId(R.id.replyText));
    textView8.check(matches(withText("???")));

    // ---------------------

    // WHEN

    mActivityTestRule.getActivity()
        .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


    // THEN

    ViewInteraction textView9 = onView(withId(R.id.questionText));
    textView9.check(matches(withText("Question #2: False")));

    ViewInteraction textView10 = onView(withId(R.id.replyText));
    textView10.check(matches(withText("???")));
  }


  @Test
  public void landscapeCheatTest() {

    // GIVEN

    ViewInteraction textView17 = onView(withId(R.id.questionText));
    textView17.check(matches(withText("Question #1: True")));

    ViewInteraction textView18 = onView(withId(R.id.replyText));
    textView18.check(matches(withText("???")));


    // WHEN

    ViewInteraction appCompatButton = onView(withId(R.id.cheatButton));
    appCompatButton.perform(click());

    // THEN & GIVEN

    ViewInteraction textView = onView(withId(R.id.warningText));
    textView.check(matches(withText("Are you sure?")));

    ViewInteraction textView2 = onView(withId(R.id.answerText));
    textView2.check(matches(withText("???")));

    // ----------------------

    // WHEN

    ViewInteraction appCompatButton2 = onView(withId(R.id.yesButton));

    appCompatButton2.perform(click());

    // THEN & GIVEN

    ViewInteraction textView3 = onView(withId(R.id.warningText));
    textView3.check(matches(withText("Are you sure?")));

    ViewInteraction textView4 = onView(withId(R.id.answerText));
    textView4.check(matches(withText("True")));

    // ----------------------

    // WHEN

    mActivityTestRule.getActivity()
        .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


    // THEN & GIVEN

    ViewInteraction textView5 = onView(withId(R.id.warningText));
    textView5.check(matches(withText("Are you sure?")));

    ViewInteraction textView6 = onView(withId(R.id.answerText));
    textView6.check(matches(withText("True")));

    // ----------------------

    // WHEN

    pressBack();


    // THEN & GIVEN

    ViewInteraction textView7 = onView(withId(R.id.questionText));
    textView7.check(matches(withText("Question #2: False")));

    ViewInteraction textView8 = onView(withId(R.id.replyText));
    textView8.check(matches(withText("???")));
  }


}
