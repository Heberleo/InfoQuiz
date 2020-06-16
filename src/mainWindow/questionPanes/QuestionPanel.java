package mainWindow.questionPanes;


import questions.*;

import javax.swing.*;
import java.awt.*;

public class QuestionPanel extends JPanel {
    CardLayout cardLayout;

    /**
     * Declare one object of each subclass of QuestionDisplay to represent the different types of questions
     */
    MultipleChoiceDisplay multipleChoiceDisplay;
    FillBlankDisplay fillBlankDisplay;

    public QuestionPanel() {
        //Create CardLayout
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        //Create QuestionDisplays
        multipleChoiceDisplay = new MultipleChoiceDisplay();
        fillBlankDisplay = new FillBlankDisplay();
        //Add questionDisplays to this Panel
        add(multipleChoiceDisplay, "" + Questiontype.MultipleChoice);
        add(fillBlankDisplay, "" + Questiontype.FillBlank);
    }
    /**
     * Calls the correct QuestionDiplay depending on the type of q and displays the question.
     * @param q an object of a Question-subclass
     */
    public void showQuestion(Question q) {
        if (q.getType().equals(Questiontype.MultipleChoice)) {
            multipleChoiceDisplay.readQuestion(q);
            cardLayout.show(this, "" + Questiontype.MultipleChoice);
        } else if (q.getType().equals(Questiontype.FillBlank)) {
            fillBlankDisplay.readQuestion(q);
            cardLayout.show(this, "" + Questiontype.FillBlank);
        }
    }
}
