package Database;

import GUI.questionPanes.IllegalQuestionException;
import Management.Question;
import java.util.ArrayList;
import java.util.Random;

/**
 * This Container contains all the Questions
 */
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

    /**
     * loads the questions into the Container and sorts them
     */
    public void load() {
        dataManagement.load(instance());
        sort();
    }

    /**
     * saves the Questions into the database
     */
    public void save() {dataManagement.save(instance());}

    /**
     * adds an new Question to the Container
     * @param q the Question to be added
     */
    public void linkQuestion(Question q) {
        //Exception
        if (list.contains(q)) throw new IllegalQuestionException("Doppelte Frage");
        if (q.isMarked()) MarkedContainer.instance().linkQuestion(q);
        list.add(q);
    }

    /**
     * Deletes a Question from the container
     * @param q the Question to be deleted
     */
    public void unlinkQuestion(Question q) {
        //Exception
        list.remove(q);
    }

    /**
     *
     * @return a random question from the Container
     */
    public Question next() {
        int id = randy.nextInt(list.size() );
        return list.get(id);
    }

    /**
     * Sorts the list by the Question id
     */
    public void sort() {
        list.sort((x, y) -> Math.max(x.getId(), y.getId()));
    }

    /**
     *
     * @return the list the Questions are stored in
     */
    public ArrayList<Question> getList() {
        return list;
    }

    /**
     *
     * @return the next unused id
     */
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