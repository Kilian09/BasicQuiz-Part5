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
public class ExtraQuizTest {

  @Rule
  public ActivityTestRule<QuestionActivity> mActivityTestRule =
      new ActivityTestRule<>(QuestionActivity.class);

  @Test
  public void quizExtraTest() {

    // GIVEN


    ViewInteraction textView = onView(withId(R.id.questionText));
    textView.check(matches(withText("Question #1: True")));

    ViewInteraction textView2 = onView(withId(R.id.replyText));
    textView2.check(matches(withText("???")));


    // WHEN

    ViewInteraction appCompatButton = onView(withId(R.id.trueButton));
    appCompatButton.perform(click());

    ViewInteraction appCompatButton2 = onView(withId(R.id.nextButton));
    appCompatButton2.perform(click());


    ViewInteraction appCompatButton3 = onView(withId(R.id.cheatButton));
    appCompatButton3.perform(click());

    ViewInteraction appCompatButton4 = onView(withId(R.id.yesButton));
    appCompatButton4.perform(click());


    mActivityTestRule.getActivity()
        .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

    //ViewInteraction textView3 = onView(withId(R.id.answerText));
    //textView3.check(matches(withText("False")));


    //delayTest(4);

    pressBack();


    mActivityTestRule.getActivity()
        .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    //ViewInteraction textView4 = onView(withId(R.id.answerText));
    //textView4.check(matches(withText("False")));

    //pressBack();


    // THEN

    ViewInteraction textView5 = onView(withId(R.id.questionText));
    textView5.check(matches(withText("Question #3: True")));

    ViewInteraction textView6 = onView(withId(R.id.replyText));
    textView6.check(matches(withText("???")));

  }


  private void delayTest(long secs) {

    try {
      Thread.sleep(secs*1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
