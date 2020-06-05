package mainWindow.questionPanes;

public class IllegalQuestionException extends RuntimeException {
    public IllegalQuestionException() {
        super("The given Question q is not of the correct subclass.");
    }
    public IllegalQuestionException(String subclass) {
        super("The given Question q is not of the correct subclass:" + subclass);
    }
}
