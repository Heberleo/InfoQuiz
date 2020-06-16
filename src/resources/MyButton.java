package resources;

import javax.swing.*;

import java.awt.*;

import static resources.MyColor.uni;

public class MyButton extends JButton {

	public MyButton(String name) {
		super(name);
		setBackground(uni);
		setForeground(Color.WHITE);
		setFocusable(false);
	}
}
