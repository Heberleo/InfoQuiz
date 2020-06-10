package mainWindow.questionPanes;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Timer extends JPanel {
	
	private JLabel seconds;
	private Container parent;

	private static final long serialVersionUID = -8879960033005462359L;

	public Timer(Container parent, int seconds) {
		this.parent = parent;
		this.seconds = new JLabel(String.valueOf(seconds));
		
		this.setBackground(Color.cyan);
		this.add(new JLabel("Sekunden übrig:"));
		this.add(this.seconds);
	}
	
	public void start() {
		
		}
	}
}