package es.ulpgc.eite.da.basicquizlab.question;

import java.lang.ref.WeakReference;

public class QuestionPresenter implements QuestionContract.Presenter {

    private final WeakReference<QuestionContract.View> view;
    private final QuestionContract.Model model;

    public QuestionPresenter(
            WeakReference<QuestionContract.View> view,
            QuestionContract.Model model) {
        this.model = model;
        this.view = view;
    }

    public void onCreate() {
        view.get().resetReplyContent();
        view.get().setCurrentQuestion(model.getCurrentQuestion());
        view.get().onRestoreLayoutData();
        view.get().updateLayoutContent();
    }

    public void onPause() {
        view.get().onSaveLayoutData(model.getCurrentIndex());
    }

    public void onTrueButtonClicked() {
        view.get().updateReplyContent(model.isCurrentReplyCorrect(true));

        buttonClicked();
    }

    public void onFalseButtonClicked() {
        view.get().updateReplyContent(model.isCurrentReplyCorrect(false));

        buttonClicked();
    }

    public void onNextButtonClicked() {
        model.incrementCurrentIndex();
        if (model.hasMoreQuestions()) {
            view.get().resetReplyContent();
            view.get().setCurrentQuestion(model.getCurrentQuestion());
            view.get().updateLayoutContent();
            view.get().disabledNextButton();
        }
    }

    public void onCheatButtonClicked() {
        view.get().startCheatActivity(model.getCurrentReply());
    }

    private void buttonClicked() {
        view.get().updateLayoutContent();
        view.get().enabledNextButton();
    }

    public void onNotificationFromCheatActivity() {
        view.get().onSaveLayoutData(model.getCurrentIndex());
    }

    public void onAnswerCheated() {
        view.get().resetAnswerCheated();
        view.get().enabledNextButton();
        onNextButtonClicked();
    }
}
