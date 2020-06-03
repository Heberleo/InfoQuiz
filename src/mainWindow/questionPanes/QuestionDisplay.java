package mainWindow.questionPanes;

import javax.swing.*;

public abstract class QuestionDisplay extends JPanel {
    public abstract void readQuestion(Question q); //get the Question/Answers/etc. from q and fill in labels, textfields ...
    public abstract void checkType(Question q); //make sure the question has the correct class, else throw Exception: tbd
}
