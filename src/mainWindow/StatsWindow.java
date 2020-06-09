package mainWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import questions.Category;

@SuppressWarnings("serial")
public class StatsWindow extends JDialog {
	
	Random r = new Random();

	private static Color lightgray = new Color(220, 220, 220);
	private static Color darkgray = new Color(200, 200, 200);
	
	private GridLayout gridLayout = new GridLayout(0, 1, 2, 2);
	private JComboBox<Category> categories;
	private JPanel questionPanel;
	private JScrollPane scrollpane;
	private JPanel questions[];

	public StatsWindow(Frame owner) {
		super(owner, "Statistiken");

		categories = new JComboBox<>(Category.values());
		categories.addActionListener(e -> init());
		
		questionPanel = new JPanel(new GridLayout(0, 1));
		
		init();
		
		scrollpane = new JScrollPane(questionPanel);
		
		this.setLayout(new BorderLayout(5, 5));
		this.add(categories, BorderLayout.NORTH);
		this.add(scrollpane, BorderLayout.CENTER);
		this.setSize(360, 500);
		this.setVisible(true);
	}

	private void init() {
		questionPanel.removeAll();
		questionPanel.setLayout(gridLayout);
		
		/*
		 * put amount of questions of the selected type as array length
		 * maybe use something like: length = QuestionGetter.getAmount(this.categories.getSelectedItem())
		 */
		questions = new JPanel[r.nextInt(30) + 150];

		for (int i = 0; i < questions.length; ++i) {
			questions[i] = new JPanel(new BorderLayout());
			questions[i].setBackground((i % 2 == 1) ? lightgray : darkgray);
			questions[i].add(new JLabel("  Frage " + (i + 1) + ": "), BorderLayout.WEST);
			questions[i].add(new JLabel(5 + "/" + 10 + " richtig beantwortet  "), BorderLayout.EAST);
			questions[i].setToolTipText("Hier kommt die Frage ausformuliert hin");
			questionPanel.add(questions[i]);
		}
		this.repaint();
		this.revalidate();
	}
}