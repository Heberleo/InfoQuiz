package GUI;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Database.QuestionContainer;
import GUI.resources.MyColor;
import Management.Question;

@SuppressWarnings("serial")
public class StatsWindow extends JDialog {

	private JPanel questionPanel;
	private JPanel questions[];

	/**
	 * Creates a JDialog that shows statistic about questions
	 * 
	 * @param owner Main window
	 */
	public StatsWindow(Frame owner) {
		super(owner, "Statistiken");

		questionPanel = new JPanel(new GridLayout(0, 1, 5, 5));
		questionPanel.setBackground(MyColor.uni);

		questions = new JPanel[QuestionContainer.instance().list.size()];

		Question q;
		int correctAnswered, wrongAnswered;

		for (int i = 0; i < questions.length; ++i) {
			q = QuestionContainer.instance().list.get(i);
			correctAnswered = q.getStats().getCorrectAnswered();
			wrongAnswered = q.getStats().getWrongAnswered();

			questions[i] = new JPanel(new BorderLayout());
			questions[i].add(new JLabel(" Frage " + q.getId() + ": "), BorderLayout.WEST);
			questions[i].add(
					new JLabel(correctAnswered + "/" + (correctAnswered + wrongAnswered) + " richtig beantwortet "),
					BorderLayout.EAST);
			questions[i].setToolTipText(q.getTitle());

			questionPanel.add(questions[i]);
		}

		this.add(new JScrollPane(questionPanel));
		this.setSize(300, 400);
		this.setResizable(false);
		this.setVisible(true);
	}
}