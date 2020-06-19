package GUI.resources;

import javax.swing.*;

import java.awt.*;

import static GUI.resources.MyColor.uni;

public class MyButton extends JButton {

	public MyButton(String name) {
		super(name);
		setBackground(uni);
		setForeground(Color.WHITE);
		setFocusable(false);
	}
}
