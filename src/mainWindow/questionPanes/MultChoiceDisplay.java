package mainWindow.questionPanes;

import javax.swing.*;
import java.awt.*;

public class MultChoiceDisplay extends QuestionDisplay {
    public MultChoiceDisplay() {
        add(new JLabel("A"), BorderLayout.CENTER);
        setBackground(Color.RED);
        setVisible(true);
    }
}
