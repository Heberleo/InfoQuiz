package GUI.questionPanes;

import GUI.resources.MyColor;

import javax.swing.*;
import java.awt.*;

/**
 * This class creates a panel, that will be shown when there are no marked questions. It has no further functionality.
 */
public class ErrorPanel extends JPanel {
	public ErrorPanel() {
		super();
		setBackground(Color.WHITE);

		JLabel lblMsg = new JLabel("Es sind noch keine Fragen markiert.");
		lblMsg.setFont(new Font(lblMsg.getFont().getName(), lblMsg.getFont().getStyle(), 20));

		lblMsg.setForeground(MyColor.uni);
		lblMsg.setAlignmentX(JLabel.CENTER);
		add(lblMsg);
	}
}
