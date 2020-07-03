package GUI;

import java.awt.*;
import java.util.Random;

import javax.swing.*;

import Database.AllContainer;
import GUI.resources.MyColor;
import Management.QuestionList;

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

		categories = new JComboBox<QuestionList>(QuestionList.values());
		categories.addActionListener(e -> init());
		categories.setBackground(MyColor.uni);
		categories.setForeground(Color.WHITE);
		categories.setFocusable(false);

		
		questionPanel = new JPanel(new GridLayout(0, 1));
		
		init();
		
		scrollpane = new JScrollPane(questionPanel);
		
		this.setLayout(new BorderLayout(5, 5));
		this.add(categories, BorderLayout.NORTH);
		this.add(scrollpane, BorderLayout.CENTER);
		this.setSize(360, 500);
		this.setVisible(true);

		UIManager.put("ToolTip.background", MyColor.uni);
		UIManager.put("ToolTip.foreground", Color.WHITE);
		UIManager.put("ComboBox.selectionBackground", MyColor.uni);
		UIManager.put("ToolTip.border", BorderFactory.createLineBorder(Color.WHITE));
	}

	private void init() {
		questionPanel.removeAll();
		questionPanel.setLayout(gridLayout);
		int length = AllContainer.instance().getList().size();
		questions = new JPanel[length];

		for (int i = 0; i < questions.length; ++i) {
			int id = AllContainer.instance().getList().get(i).getId();
			int correct = AllContainer.instance().getList().get(i).getStats().getCorrectAnswered();
			int wrong = AllContainer.instance().getList().get(i).getStats().getWrongAnswered();
			String question = AllContainer.instance().getList().get(i).getTitle();
			questions[i] = new JPanel(new BorderLayout());
			questions[i].setBackground((i % 2 == 1) ? lightgray : darkgray);
			questions[i].add(new JLabel("  Frage " + (id) + ": "), BorderLayout.WEST);
			questions[i].add(new JLabel(correct + "/" + wrong + " richtig beantwortet  "), BorderLayout.EAST);
			questions[i].setToolTipText(question);
			setPreferredSize(new Dimension(0, 20));
			questionPanel.add(questions[i]);
		}
		this.repaint();
		this.revalidate();

	}
}