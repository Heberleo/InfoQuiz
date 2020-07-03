package Database;

import GUI.questionPanes.IllegalQuestionException;
import Management.MultipleChoice;
import Management.Question;
import jdk.jfr.DataAmount;

import java.util.ArrayList;
import java.util.Random;

public class AllContainer implements QuestionContainer {
    private static AllContainer container = null;
    private ArrayList<Question> list;
    private DataManagement dataManagement;
    Random randy;

    private AllContainer() {
        list = new ArrayList<Question>();
        randy = new Random();
        dataManagement = new QuestionImport();
    }

    public static AllContainer instance() {
        if (container == null) {
            container = new AllContainer();
        }
        return container;
    }

    public void load() {
        dataManagement.load(instance());
        sort();
    }
    public void save() {dataManagement.save(instance());}
    public void linkQuestion(Question q) {
        //Exception
        if (list.contains(q)) throw new IllegalQuestionException("Doppelte Frage");
        list.add(q);
    }

    public void unlinkQuestion(Question q) {
        //Exception
        list.remove(q);
        dataManagement.delete(q, instance());
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

    public int getNextID() {
        int id = 0;
        for (Question q : list) {
            if (q.getId() > id) {
                id = q.getId();
            }
        }
        return ++id;
    }
}
