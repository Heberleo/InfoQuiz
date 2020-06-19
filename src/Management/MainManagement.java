package Management;


import Database.DBConncetion;
import Database.DBGetQuestion;
import java.beans.PropertyChangeListener;


/**
 * Diese Klasse soll den gesamten Datenverkehr zwischen GUI und DatenHaltung kontrollieren. Das MainWondow soll in seinen
 * verschiedenen actionPerformed()-Methoden auf entsprechende statische Methoden dieser Klasse zugreifen. Das QuestionPanel
 * hat einen wird bei PropertyChanges der currentQuestion aktiv.
 */
public class MainManagement {
    private static CurrentQuestion currentQuestion;
    private static DBGetQuestion questiongetter;


    /**
     * This Method sets up the Database-connection and initializes this classes attributes. Has to be called at the very
     * beginning, even before the MainWindow constructor.
     */
    public static void init() {
        questiongetter = new DBGetQuestion();
        currentQuestion = new CurrentQuestion();
        // Datenbank connect
        DBConncetion.connect();
        //
    }

    /**
     * This Method will close the Database-connection and shut down the programm.
     */
    public static void close() {
        DBConncetion.closeConnection();
        System.exit(0);
    }

    /**
     *
     * @return true, if the question is marked
     */
    public static boolean hitNext() {
        //writeback
        Question q = questiongetter.getRandomQuestion();
        currentQuestion.setQuestion(q);
        return currentQuestion.getQuestion().isMarked();
    }

    /**
     *
     * @return true, if the question is marked
     */
    public static boolean hitMarked() {
        currentQuestion.getQuestion().hitMarked();
        return currentQuestion.getQuestion().isMarked();
    }

    public static void addQuestionListener(PropertyChangeListener l) {
        currentQuestion.addPropertyChangeListener(l);
    }

}
