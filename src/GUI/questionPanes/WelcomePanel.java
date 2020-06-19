package GUI.questionPanes;

import GUI.resources.MyColor;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {

    public WelcomePanel() {
        super();
        setBackground(Color.WHITE);
        JLabel lblHello = new JLabel("Hallo.");
        lblHello.setForeground(MyColor.uni);
        add(lblHello, BorderLayout.CENTER);
    }
}
