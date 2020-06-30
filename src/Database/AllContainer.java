package Database;

import GUI.questionPanes.IllegalQuestionException;
import Management.MultipleChoice;
import Management.Question;

import java.util.ArrayList;
import java.util.Random;

public class AllContainer {
    private static AllContainer container = null;
    private ArrayList<Question> list;
    private DataManagement dataManagement;
    Random randy;

    private AllContainer() {
        list = new ArrayList<Question>();
        randy = new Random();
        //dataManagement = new QuestionImport();
    }

    public static AllContainer instance() {
        if (container == null) {
            container = new AllContainer();
        }
        return container;
    }

    public void load() {
        dataManagement.load(instance());
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
        int id = randy.nextInt(list.size() );
        return list.get(id);
    }

    public void sort() {
        list.sort((x, y) -> Math.max(x.getId(), y.getId()));
    }

    public ArrayList<Question> getList() {
        return list;
    }
}
