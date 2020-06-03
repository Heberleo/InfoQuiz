package mainWindow.questionPanes;

public class FillBlankDisplay extends QuestionDisplay {
    //component declaration

    //
    public FillBlankDisplay() {

    }
    @Override
    public void readQuestion(Question q) throws IllegalQuestionException {
        if (!checkType(q)) throw new IllegalQuestionException("FillBlank");

    }
    @Override
    public boolean checkType(Question q) {
        if (q.getClass().equals(FillBlank.class)) return true;
        return  false;
    }
}
