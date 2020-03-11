package es.ulpgc.eite.da.basicquizlab;

import android.app.Application;
import android.os.Bundle;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.basicquizlab.question.QuestionContract;

public class AppMediator extends Application {

    private Bundle questionState = null;
    private WeakReference<QuestionContract.View> questionView;

    public Bundle getQuestionState() {
        return questionState; //state = null
    }

    public void setQuestionState(Bundle state) {
        this.questionState = state;
    }

    public void setCheatToQuestionState(Bundle state) {
        questionView.get().notificationFromCheatActivity(state);
    }

    public void setCheatActivityObserver(
            WeakReference<QuestionContract.View> view) {
        this.questionView = view;
    }


}
