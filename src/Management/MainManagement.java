package Management;


import Database.DBConncetion;
import Database.AllContainer;
import Database.MarkedContainer;
import Database.QuestionImport;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/**
 * Diese Klasse soll den gesamten Datenverkehr zwischen GUI und DatenHaltung kontrollieren. Das MainWindow soll in seinen
 * verschiedenen actionPerformed()-Methoden auf entsprechende statische Methoden dieser Klasse zugreifen. Das QuestionPanel
 * hat einen wird bei PropertyChanges der currentQuestion aktiv.
 */
public class MainManagement {
    private static CurrentQuestion currentQuestion;
    private static QuestionImport questionImport;
    private static QuestionList questionList = QuestionList.ALL;

    /**
     * This Method sets up the Database-connection and initializes this classes attributes. Has to be called at the very
     * beginning, even before the MainWindow constructor.
     */
    public static void init() {
        currentQuestion = new CurrentQuestion();
        // Datenbank connect
        DBConncetion.connect();
        AllContainer.instance().load();
        DBConncetion.closeConnection();

        // set the QuestionList to start with
        questionList = QuestionList.ALL;
    }

    /**
     * This Method will close the Database-connection and shut down the programm.
     */
    public static void close() {
        //DBConncetion.connect();
        //QuestionContainer.instance().writeBack()
        //DBConncetion.closeConnection();
        System.exit(0);
    }

    /**
     * Gets the next question from the selected Filter and sets the currentQuestion. The Question can be empty/ null, if
     * the selected Filter is empty.
     * @return true/false, if the question is marked/unmarked, and false if the question is null
     */
    public static boolean next() {
        Question q;
        if (questionList == QuestionList.ALL)
            q = AllContainer.instance().next();
        else if (questionList == QuestionList.MARKED)
            q = MarkedContainer.instance().next();
        else
            q = null;
        currentQuestion.setQuestion(q);
        if (currentQuestion.isEmpty())
            return false;
        return currentQuestion.getQuestion().isMarked();
    }

    /**
     * Flips the question's marked attribute and links/ unlinks it to the MarkedContainer.
     * @return true, if the question is marked
     */
    public static boolean mark() {
        if (currentQuestion.isEmpty()) //if the question is empty, nothing should happen
            return false;
        currentQuestion.getQuestion().hitMarked();
        if (currentQuestion.getQuestion().isMarked()) {
            MarkedContainer.instance().linkQuestion(currentQuestion.getQuestion());
        } else {
            MarkedContainer.instance().unlinkQuestion(currentQuestion.getQuestion());
        }
        return currentQuestion.getQuestion().isMarked();
    }

    public static void setQuestionList(QuestionList list) {
        questionList  = list;
    }

    public static QuestionList getQuestionList() {
        return questionList;
    }

    public static void addQuestionListener(PropertyChangeListener l) {
        currentQuestion.addPropertyChangeListener(l);
    }

}
