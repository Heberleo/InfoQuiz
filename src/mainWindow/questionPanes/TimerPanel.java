package mainWindow.questionPanes;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TimerPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = -8879960033005462359L;

	private JLabel lblSeconds;
	private Timer timer;

	/**
	 * Creates a new JPanel with a timer counting down seconds.
	 */
	public TimerPanel() {
		this.lblSeconds = new JLabel();

		timer = new Timer(1000, this);

		this.setLayout(new GridBagLayout());
		this.add(new JLabel("Sekunden übrig: "));
		this.add(this.lblSeconds);
	}

	/**
	 * Starts the timer counting down. When reaching zero, it will stop
	 * automatically.
	 * 
	 * @param seconds value the counter is starting with
	 */
	public void start(int seconds) {
		this.lblSeconds.setText(String.valueOf(Math.abs(seconds)));
		timer.start();
	}

	/**
	 * Forces the timer to stop.
	 */
	public void stop() {
		timer.stop();
	}

	/**
	 * Called when timer reached zero.
	 */
	private void timeOut() {
		// deactivate current question here
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int sec = Integer.valueOf(lblSeconds.getText());
		if (sec > 0) {
			sec--;
			lblSeconds.setText(String.valueOf(sec));
			repaint();
		} else {
			((Timer) e.getSource()).stop();
			timeOut();
		}
	}
}