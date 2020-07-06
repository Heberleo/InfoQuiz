package GUI;

import static GUI.resources.MyColor.uni;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import GUI.questionPanes.QuestionPanel;
import GUI.resources.MyButton;
import Management.MainManagement;
import Management.PointCounter;
import Management.QuestionList;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements ActionListener {
	private QuestionPanel mainQuestionPanel; // contains the different Question panels;
	private JPanel mainButtonPanel; // contains Buttons
	private MyButton markedButton; // to save question
	private MyButton nextButton; // to get next Question
	private JMenuBar menuBar;
	private JMenu lists; // to navigate different Lists of Questions
	private JMenuItem allQuestions;
	private JMenuItem myList;
	private JMenuItem stats; // to go to Stats
	private JMenuItem dailyChallenge; // to Access dailyChallenges
	private JMenuItem pointsItem; // point counter
	private StatsWindow statsWindow; // JDialog showing statistics
	private WindowListener windowListener;

	private int points;

	public MainWindow() {
		super("InfoQuiz");
		ImageIcon icon = new ImageIcon("lib/images/icon32.png");
		setIconImage(icon.getImage());
		// QuestionPanel
		mainQuestionPanel = new QuestionPanel();
		mainQuestionPanel.setBackground(Color.RED); // Just a Test
		// ButtonPanel
		markedButton = new MyButton("markieren");
		markedButton.setPreferredSize(new Dimension(100, 30));
		markedButton.addActionListener(this);
		nextButton = new MyButton("nächste");
		nextButton.setPreferredSize(new Dimension(100, 30));
		nextButton.addActionListener(this);
		FlowLayout fl = new FlowLayout(FlowLayout.RIGHT);
		fl.setHgap(15);
		mainButtonPanel = new JPanel(fl);
		mainButtonPanel.setBackground(Color.WHITE);
		mainButtonPanel.add(markedButton);
		mainButtonPanel.add(nextButton);
		// Menu
		menuBar = new JMenuBar();
		menuBar.setBackground(uni);
		lists = new JMenu("Alle Fragen");
		lists.setPreferredSize(new Dimension(105, 20));
		lists.setBackground(uni);
		lists.setForeground(Color.WHITE);
		stats = new JMenuItem("Statistiken");
		stats.setBackground(uni);
		stats.setForeground(Color.WHITE);
		stats.setPreferredSize(new Dimension(70, 20));
		stats.setMaximumSize(new Dimension(70, 20));
		stats.addActionListener(this);

		points = PointCounter.instance().getPoints();
		PointCounter.instance().addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				points = (int) evt.getNewValue();
				refreshPointsItem();
			}
		});

		pointsItem = new JMenuItem();
		pointsItem.setBackground(uni);
		pointsItem.setForeground(Color.WHITE);
		pointsItem.setPreferredSize(new Dimension(118, 20));
		pointsItem.setMaximumSize(pointsItem.getPreferredSize());
		refreshPointsItem();

		menuBar.add(lists);
		myList = new JMenuItem("Markierte Fragen");
		myList.setBackground(Color.WHITE);
		myList.setForeground(uni);
		myList.addActionListener(this);
		allQuestions = new JMenuItem("Alle Fragen");
		allQuestions.setBackground(Color.WHITE);
		allQuestions.setForeground(uni);
		allQuestions.addActionListener(this);
		lists.add(myList);
		lists.add(allQuestions);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(stats);
		menuBar.add(pointsItem);


		setJMenuBar(menuBar);

		// Add together
		add(mainQuestionPanel, BorderLayout.CENTER);
		add(mainButtonPanel, BorderLayout.SOUTH);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(700, 700);
		setVisible(true);

		// windowListener
		windowListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MainManagement.close();
			}
		};
		addWindowListener(windowListener);
	}

	private void refreshPointsItem() {
		String formatted = String.format("%04d", this.points);
		this.pointsItem.setText("Punktestand: " + formatted);
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Menu actions
		if (e.getSource().equals(myList)) {
			MainManagement.setQuestionList(QuestionList.MARKED);
			setQuestionMenu(QuestionList.MARKED);
		}
		if (e.getSource().equals(allQuestions)) {
			MainManagement.setQuestionList(QuestionList.ALL);
			setQuestionMenu(QuestionList.ALL);
		}
		if (e.getSource().equals(stats)) {
			System.out.println("stats");
			if (statsWindow == null || !statsWindow.isVisible())
				statsWindow = new StatsWindow(this);
		}
		// Button Actions
		if (e.getSource().equals(nextButton)) {
			setMarkedButton(MainManagement.next());
		}
		if (e.getSource().equals(markedButton)) {
			setMarkedButton(MainManagement.mark());
		}
	}

	/**
	 * Changes the text/ look of the markedButton, depending of the marked-state of
	 * the loaded Question
	 *
	 * @param marked true for marked, false for unmarked
	 */
	private void setMarkedButton(boolean marked) {
		if (marked) {
			markedButton.setText("markiert");
		} else {
			markedButton.setText("markieren");
		}
	}

	private void setQuestionMenu(QuestionList filter) {
		lists.setText(filter.toString());
	}
}
