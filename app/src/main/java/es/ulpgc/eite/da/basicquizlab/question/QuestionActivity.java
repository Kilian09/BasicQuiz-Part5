package es.ulpgc.eite.da.basicquizlab.question;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.basicquizlab.AppMediator;
import es.ulpgc.eite.da.basicquizlab.cheat.CheatActivity;
import es.ulpgc.eite.da.basicquizlab.R;

public class QuestionActivity
        extends AppCompatActivity implements QuestionContract.View {

    public static final String TAG = "Quiz.QuestionActivity";

    public final static String KEY_INDEX = "KEY_INDEX";
    public final static String KEY_REPLY = "KEY_REPLY";
    public final static String KEY_ENABLED = "KEY_ENABLED";
    public final static String KEY_CHEATED = "KEY_CHEATED";

    private Button falseButton, trueButton, cheatButton, nextButton;
    private TextView questionText, replyText;

    private boolean nextButtonEnabled;
    private String currentQuestion;
    private String currentReply;
    private QuestionContract.Presenter presenter;
    private AppMediator mediator;
    private boolean answerCheated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        getSupportActionBar().setTitle(R.string.question_title);

        linkLayoutComponents();
        initLayoutContent();

        initArchComponents();
        presenter.onCreate();

    }

    private void linkLayoutComponents() {
        falseButton = findViewById(R.id.falseButton);
        trueButton = findViewById(R.id.trueButton);
        cheatButton = findViewById(R.id.cheatButton);
        nextButton = findViewById(R.id.nextButton);

        questionText = findViewById(R.id.questionText);
        replyText = findViewById(R.id.replyText);
    }

    private void initLayoutContent() {
        falseButton.setText(R.string.false_button_text);
        trueButton.setText(R.string.true_button_text);
        nextButton.setText(R.string.next_button_text);
        cheatButton.setText(R.string.cheat_button_text);
    }

    private void initArchComponents() {

        mediator = (AppMediator) getApplication();

        String[] questions = getResources()
                .getStringArray(R.array.question_array);
        int[] replies = getResources().getIntArray(R.array.reply_array);

        WeakReference<QuestionContract.View> view =
                new WeakReference<>((QuestionContract.View) this);


        mediator.setCheatActivityObserver(view);

        QuestionContract.Model model = new QuestionModel(questions, replies);
        presenter = new QuestionPresenter(view, model);

    }

    protected void onResume() {
        super.onResume();

        if (answerCheated) { //cheated = false
            presenter.onAnswerCheated();
        }
    }

    protected void onPause() {
        super.onPause();

        presenter.onPause();
    }

    public void setCurrentQuestion(String question) {
        this.currentQuestion = question; // question = "Question #1: True"
    }

    public void resetReplyContent() {
        currentReply = getString(R.string.empty_text); //reply = "???"
    }

    public void onRestoreLayoutData() {
        Bundle savedState = mediator.getQuestionState();

        if (savedState == null)
            return;
    }

    public void updateLayoutContent() {
        questionText.setText(currentQuestion); // "Question #1: True"
        replyText.setText(currentReply);      // "???"
    }

    public void updateReplyContent(boolean isReplyCorrect) {
        if (isReplyCorrect) {
            currentReply = getString(R.string.correct_text); // reply = "correct"
        } else {
            currentReply = getString(R.string.incorrect_text);
        }
    }

    public void enabledNextButton() {
        nextButtonEnabled = true;
    }

    public void disabledNextButton() {
        nextButtonEnabled = false;
    }

    public void onButtonClick(View view) {

        switch (view.getId()) {
            case R.id.falseButton:
                if (!nextButtonEnabled) {
                    presenter.onFalseButtonClicked();
                }
            case R.id.trueButton:
                if (!nextButtonEnabled) {
                    presenter.onTrueButtonClicked();
                }
                break;
            case R.id.nextButton:
                if (nextButtonEnabled) {
                    presenter.onNextButtonClicked();
                }
                break;
            case R.id.cheatButton:
                if (!nextButtonEnabled) {
                    presenter.onCheatButtonClicked();
                }
        }

    }

    public void startCheatActivity(int reply) {

        Intent intent = new Intent(this, CheatActivity.class);
        intent.putExtra(CheatActivity.EXTRA_ANSWER, reply);
        startActivity(intent);
    }

    public void onSaveLayoutData(int questionIndex) {
        Bundle outState = new Bundle();
        outState.putInt(KEY_INDEX, questionIndex);
        outState.putString(KEY_REPLY, currentReply);
        outState.putBoolean(KEY_ENABLED, nextButtonEnabled);
        outState.putBoolean(KEY_CHEATED, answerCheated);

        mediator.setQuestionState(outState);
    }

    public void notificationFromCheatActivity(Bundle state) {
        answerCheated = state.getBoolean(CheatActivity.EXTRA_CHEATED, false);
        presenter.onNotificationFromCheatActivity();
    }

    public void resetAnswerCheated() {
        answerCheated = false;
    }
}
