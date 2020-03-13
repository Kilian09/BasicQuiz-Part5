package es.ulpgc.eite.da.basicquizlab.question;

import android.util.Log;

import java.lang.ref.WeakReference;

import static es.ulpgc.eite.da.basicquizlab.question.QuestionActivity.TAG;

public class QuestionPresenter implements QuestionContract.Presenter {

    private final WeakReference<QuestionContract.View> view;
    private final QuestionContract.Model model;

    public QuestionPresenter(
            WeakReference<QuestionContract.View> view,
            QuestionContract.Model model) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onCreate() {
        view.get().resetReplyContent();
        view.get().setCurrentQuestion(model.getCurrentQuestion());
        view.get().onRestoreLayoutData();
        view.get().updateLayoutContent();
    }

    @Override
    public void onPause() {
        view.get().onSaveLayoutData(model.getCurrentIndex());
    }

    @Override
    public void onTrueButtonClicked() {
        view.get().updateReplyContent(model.isCurrentReplyCorrect(true));

        buttonClicked();
    }

    @Override
    public void onFalseButtonClicked() {
        view.get().updateReplyContent(model.isCurrentReplyCorrect(false));

        buttonClicked();
    }

    @Override
    public void onNextButtonClicked() {
        model.incrementCurrentIndex();
        if (model.hasMoreQuestions()) {
            view.get().resetReplyContent();
            view.get().setCurrentQuestion(model.getCurrentQuestion());
            view.get().updateLayoutContent();
            view.get().disabledNextButton();
        }
    }

    @Override
    public void onCheatButtonClicked() {
        view.get().startCheatActivity(model.getCurrentReply());
    }


    private void buttonClicked() {
        view.get().updateLayoutContent();
        view.get().enabledNextButton();
    }
    @Override
    public void onNotificationFromCheatActivity() {
        view.get().onSaveLayoutData(model.getCurrentIndex());
    }
    @Override
    public void onAnswerCheated() {
        view.get().resetAnswerCheated();
        view.get().enabledNextButton();
        onNextButtonClicked();
    }

    @Override
    public void onQuestionIndexUpdated(int index) {
        Log.d(TAG, "onQuestionIndexUpdated()");
        Log.d(TAG, "index: " + index);

        model.setCurrentIndex(index);
        view.get().setCurrentQuestion(model.getCurrentQuestion());
    }

}
