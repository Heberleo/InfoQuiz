package GUI.questionPanes;

import GUI.resources.MyColor;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {

    public WelcomePanel() {
        super();
        setLayout(new GridLayout(2,1));
        ImageIcon icon = new ImageIcon(("lib/images/icon.png"));
        setBackground(Color.WHITE);

        JLabel lblHello = new JLabel();
        lblHello.setIcon(icon);
        lblHello.setForeground(MyColor.uni);
        lblHello.setAlignmentX(JLabel.CENTER);
        add(lblHello);

        TextArea taHello = new TextArea("Hallo");
        add(taHello);
    }


}
