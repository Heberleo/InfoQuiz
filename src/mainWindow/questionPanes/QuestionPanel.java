package mainWindow.questionPanes;

import javax.swing.*;
import java.awt.*;

public class QuestionPanel extends JPanel {
    CardLayout cardLayout;
    boolean test = true;

    /**
     * Declare one object of each subclass of QuestionDisplay to represent the different types of questions
     */
    MultChoiceDisplay multChoiceDisplay;
    GuessDisplay guessDisplay;

    public QuestionPanel() {
        //Create CardLayout
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        //Create QuestionDiplays
        multChoiceDisplay = new MultChoiceDisplay();
        guessDisplay = new GuessDisplay();
        //Add questionDiplays to this Panel
        add(multChoiceDisplay, "multChoiceDisplay");
        add(guessDisplay, "guessDisplay");
    }


    public void switchPanel() {
        if (test) {
            cardLayout.show(this, "guessDisplay");
            test = !test;
        } else {
            cardLayout.show(this, "multChoiceDisplay");
            test = !test;
        }
    }
}
