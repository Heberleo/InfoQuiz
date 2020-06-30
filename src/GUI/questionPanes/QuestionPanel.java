package GUI.questionPanes;


import Management.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class QuestionPanel extends JPanel implements PropertyChangeListener {
    CardLayout cardLayout;

    /**
     * Declare one object of each subclass of QuestionDisplay to represent the different types of questions
     */
    MultipleChoiceDisplay multipleChoiceDisplay;
    FillBlankDisplay fillBlankDisplay;
    QuestionDisplay currentDisplay;
    WelcomePanel welcomePanel;
    ErrorPanel errorPanel;

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

        //welcomePanel will be selected at the beginning
        welcomePanel = new WelcomePanel();
        add(welcomePanel, "welcomePanel");
        cardLayout.show(this, "welcomePanel");

        // ErrorPanel
        errorPanel = new ErrorPanel();
        add(errorPanel, "errorPanel");

        //add this as PropertyChangeListener to the MainManagement.CurrentQuestion
        MainManagement.addQuestionListener(this);
    }

    /**
     * Activates the questionDisplay, that matches the questionType.
     * If the new question is empty, the errorPanel will be shown and nothing else happens.
     * @param evt fired, when the currentQuestion in MainManagement is changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Question q = (Question) evt.getNewValue();
        if (q == null) {
            cardLayout.show(this, "errorPanel");
        } else if (q.getType().equals(Questiontype.MultipleChoice)) {
            currentDisplay = multipleChoiceDisplay;
            multipleChoiceDisplay.readQuestion(q);
            cardLayout.show(this, "" + Questiontype.MultipleChoice);
        } else if (q.getType().equals(Questiontype.FillBlank)) {
            currentDisplay = fillBlankDisplay;
            fillBlankDisplay.readQuestion(q);
            cardLayout.show(this, "" + Questiontype.FillBlank);
        }
    }

}
