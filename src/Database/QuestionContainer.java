package Database;

import GUI.questionPanes.IllegalQuestionException;
import Management.FillBlank;
import Management.MultipleChoice;
import Management.Question;

import java.util.ArrayList;

public class QuestionContainer {
    private static QuestionContainer container = null;
    public ArrayList<Question> list;
    private int count;

    private QuestionContainer() {
        list = new ArrayList<Question>();
        count = 0;
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
        MultipleChoice q = (MultipleChoice) list.get(count);
        return list.get(count++);
    }

    public void sort() {
        list.sort((x, y) -> Math.max(x.getId(), y.getId()));
    }
}
