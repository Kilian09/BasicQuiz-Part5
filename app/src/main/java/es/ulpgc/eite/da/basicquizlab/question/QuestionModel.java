package es.ulpgc.eite.da.basicquizlab.question;

public class QuestionModel implements QuestionContract.Model {

    private final String[] questionArray;
    private final int[] replyArray;
    private int questionIndex;

    public QuestionModel(String[] questions, int[] replies) {
        this.questionArray = questions;
        this.replyArray = replies;
    }

    public String getCurrentQuestion() {
        return questionArray[questionIndex]; //question = "Question #1: True"
    }

    public int getCurrentReply() {
        return replyArray[questionIndex];
    }

    public int getCurrentIndex() {
        return questionIndex;
    }

    public void setCurrentIndex(int index) {
        this.questionIndex = index;
    }

    public boolean isCurrentReplyCorrect(boolean isReplyTrue) {
        if (isReplyTrue) {
            return checkReplyWhenTrueButtonClicked();
        } else {
            return checkReplyWhenFalseButtonClicked();
        }
    }

    private boolean checkReplyWhenTrueButtonClicked() {
        if (replyArray[questionIndex] == 1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkReplyWhenFalseButtonClicked() {
        if (replyArray[questionIndex] == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void incrementCurrentIndex() {
        questionIndex++; //index = 1
    }

    public boolean hasMoreQuestions() {
        checkCurrentIndex();

        if (questionIndex < questionArray.length) {
            return true;
        }
        return false;
    }

    private void checkCurrentIndex() {
        if (questionIndex == questionArray.length) {
            questionIndex = 0;
        }
    }
}
