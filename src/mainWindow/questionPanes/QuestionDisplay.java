package mainWindow.questionPanes;



import questions.*;

import javax.swing.*;

public abstract class QuestionDisplay extends JPanel {
    /**
     * Puts the question/ answers/ ... into the GUI.
     * @param q an object of a Question-subclass
     */
    public abstract void readQuestion(Question q); //get the Question/Answers/etc. from q and fill in labels, textfields ...
    public abstract boolean checkType(Question q); //make sure the question has the correct class, else throw Exception: tbd
    public abstract boolean checkAnswer();
}
