package mainWindow.questionPanes;

import javax.swing.*;
import java.awt.*;

public class MultipleChoiceDisplay extends QuestionDisplay {
    //component declaration

    //
    public MultipleChoiceDisplay() {
        add(new JLabel("A"), BorderLayout.CENTER);
        setBackground(Color.RED);
        setVisible(true);
    }
    @Override
    public void readQuestion(Question q) throws IllegalQuestionException {
        if (!checkType(q)) throw new IllegalQuestionException("MultipleChoice");

    }
    @Override
    public boolean checkType(Question q) {
        if (q.getClass().equals(MultipleChoice.class)) return true;
        return  false;
    }



}
