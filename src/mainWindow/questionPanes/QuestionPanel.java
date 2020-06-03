package mainWindow.questionPanes;

import javax.swing.*;
import java.awt.*;

public class QuestionPanel extends JPanel {
    CardLayout cardLayout;
    boolean test = true;

    /**
     * Declare one object of each subclass of QuestionDisplay to represent the different types of questions
     */
    MultipleChoiceDisplay multipleChoiceDisplay;
    GuessDisplay guessDisplay;
    FillBlankDisplay fillBlankDisplay;

    public QuestionPanel() {
        //Create CardLayout
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        //Create QuestionDiplays
        multipleChoiceDisplay = new MultipleChoiceDisplay();
        guessDisplay = new GuessDisplay();
        fillBlankDisplay = new FillBlankDisplay();
        //Add questionDiplays to this Panel
        add(multipleChoiceDisplay, "MultipleChoice");
        add(guessDisplay, "Guess");
        add(fillBlankDisplay, "FillBlank");
    }


    public void switchPanel() {
        if (test) {
            cardLayout.show(this, "Guess");
            test = !test;
        } else {
            cardLayout.show(this, "MultipleChoice");
            test = !test;
        }
    }
    //public void showQuestion(Question q) {
    /**
     * Depending on the type/ class of q, call readQuestion(q) on the corresponding QuestionDiplay and do cardLayout.show(this, "type_of_q");
     */
    //}
}
