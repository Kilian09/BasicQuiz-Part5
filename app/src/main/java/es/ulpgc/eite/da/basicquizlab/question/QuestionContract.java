package es.ulpgc.eite.da.basicquizlab.question;

import android.os.Bundle;

public interface QuestionContract {

    interface View {

        void notificationFromCheatActivity(Bundle state);
        void resetReplyContent();
        void onRestoreLayoutData();
        void updateLayoutContent();
        void setCurrentQuestion(String currentQuestion);
        void enabledNextButton();
        void updateReplyContent(boolean currentReplyCorrect);
        void disabledNextButton();
        void startCheatActivity(int currentReply);
        void onSaveLayoutData(int currentIndex);
        void resetAnswerCheated();
    }

    interface Presenter {

        void onCreate();
        void onAnswerCheated();
        void onTrueButtonClicked();
        void onFalseButtonClicked();
        void onNextButtonClicked();
        void onCheatButtonClicked();
        void onPause();
        void onNotificationFromCheatActivity();
        void onQuestionIndexUpdated(int anInt);
    }

    interface Model {

        String getCurrentQuestion();
        boolean isCurrentReplyCorrect(boolean isReplyTrue);
        void incrementCurrentIndex();
        boolean hasMoreQuestions();
        int getCurrentReply();
        int getCurrentIndex();
        void setCurrentIndex(int index);
    }
}
