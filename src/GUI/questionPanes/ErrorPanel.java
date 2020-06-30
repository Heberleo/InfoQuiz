package GUI.questionPanes;

import GUI.resources.MyColor;

import javax.swing.*;
import java.awt.*;

public class ErrorPanel extends JPanel {
	public ErrorPanel() {
		super();
		setBackground(Color.WHITE);

		JLabel lblMsg = new JLabel("Sorry, Fragen sind alle.");

		lblMsg.setForeground(MyColor.uni);
		lblMsg.setAlignmentX(JLabel.CENTER);
		add(lblMsg);
	}
}
