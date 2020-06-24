package Database;

import GUI.questionPanes.IllegalQuestionException;
import Management.FillBlank;
import Management.Question;

import java.util.ArrayList;

public class QuestionContainer {
    private static QuestionContainer container = null;
    ArrayList<Question> list;

    private QuestionContainer() {
        list = new ArrayList<Question>();
    }

    public static QuestionContainer instance() {
        if (container == null) {
            container = new QuestionContainer();
        }
        return container;
    }

    public void linkQuestion(Question q) {
        //Exception
        if (list.contains(q)) throw new IllegalQuestionException("Doppelte Frage");
        list.add(q);
    }

    public void unlinkQuestion(Question q) {
        //Exception
        list.remove(q);
    }

    public Question next() {
        return new FillBlank("hallo", "welt");
    }
}
