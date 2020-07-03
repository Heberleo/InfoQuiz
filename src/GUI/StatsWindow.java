package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Database.AllContainer;
import Database.MarkedContainer;
import Database.QuestionContainer;
import GUI.resources.MyColor;
import Management.QuestionList;

@SuppressWarnings("serial")
public class StatsWindow extends JDialog {

	private GridLayout gridLayout = new GridLayout(0, 1, 7, 7);
	private JComboBox<QuestionList> categories;
	private JPanel questionPanel;
	private JScrollPane scrollpane;
	private JPanel questions[];

	private QuestionContainer container;

	public StatsWindow(Frame owner) {
		super(owner, "Statistiken");

		container = AllContainer.instance();

		categories = new JComboBox<QuestionList>(QuestionList.values());
		categories.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (categories.getSelectedItem().toString()) {
					case "MARKED":
						container = MarkedContainer.instance();
						break;
					default:
						container = AllContainer.instance();
						break;
				}
				init();
			}
		});
		categories.setBackground(MyColor.uni);
		categories.setForeground(Color.WHITE);
		categories.setFocusable(false);

		questionPanel = new JPanel(new GridLayout(0, 1));
		questionPanel.setBorder(new EmptyBorder(7, 7, 7, 7));
		questionPanel.setBackground(MyColor.uni);

		init();

		scrollpane = new JScrollPane(questionPanel);

		this.setLayout(new BorderLayout(5, 5));
		this.add(categories, BorderLayout.NORTH);
		this.add(scrollpane, BorderLayout.CENTER);
		this.setSize(360, 500);
		this.setVisible(true);

		UIManager.put("ToolTip.background", MyColor.uni);
		UIManager.put("ToolTip.foreground", Color.WHITE);
		UIManager.put("ToolTip.border", BorderFactory.createLineBorder(Color.WHITE));
	}

	private void init() {
		questionPanel.removeAll();
		questionPanel.setLayout(gridLayout);

		Border border = BorderFactory.createEmptyBorder(4, 5, 4, 5);

		int length = container.getList().size();
		questions = new JPanel[length];

		for (int i = 0; i < questions.length; ++i) {

			int correct = container.getList().get(i).getStats().getCorrectAnswered();
			int wrong = container.getList().get(i).getStats().getWrongAnswered();
			String question = container.getList().get(i).getTitle();

			questions[i] = new JPanel(new BorderLayout());
			questions[i].setBorder(border);
//			questions[i].add(new JLabel("<html>Frage " + (id) + ": "), BorderLayout.WEST);
			questions[i].add(new JLabel(question), BorderLayout.WEST);
			questions[i].add(new JLabel(correct + "/" + (correct + wrong) + " richtig beantwortet"), BorderLayout.EAST);
			questions[i].setToolTipText(question);
			setPreferredSize(new Dimension(0, 20));

			questionPanel.add(questions[i]);
		}

		this.repaint();
		this.revalidate();
	}
}