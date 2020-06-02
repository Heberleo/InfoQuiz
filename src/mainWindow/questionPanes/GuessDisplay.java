package mainWindow.questionPanes;

import javax.swing.*;
import java.awt.*;

public class GuessDisplay extends QuestionDisplay {
    public GuessDisplay() {
        add(new JLabel("B"), BorderLayout.CENTER);
        setBackground(Color.BLUE);
        setVisible(true);
    }
}
