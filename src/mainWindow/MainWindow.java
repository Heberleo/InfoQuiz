package mainWindow;

import mainWindow.questionPanes.QuestionPanel;
import questions.FillBlank;
import questions.MultipleChoice;
import resources.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PrivateKey;

import static resources.MyColor.uni;

public class MainWindow extends JFrame implements ActionListener{
    private QuestionPanel mainQuestionPanel; //contains the different Question panels;
    private JPanel mainButtonPanel; //contains Buttons
    private MyButton saveButton; //to save question
    private MyButton nextButton; //to get next Question
    private MyButton prevButton; //to get previous Question
    private JMenuBar menuBar;
    private JMenu lists; // to navigate different Lists of Questions
        private JMenuItem allQuestions;
        private JMenuItem wrongQuestions;
        private JMenuItem myList;
    private JMenuItem stats; // to go to Stats
    private JMenuItem dailyChallenge; // to Access dailyChallenges
    private StatsWindow statsWindow;


    public MainWindow() {
        super("InfoQuiz");
        //QuestionPanel
        mainQuestionPanel = new QuestionPanel();
        mainQuestionPanel.setBackground(Color.RED); // Just a Test
        //ButtonPanel
        saveButton = new MyButton("save");
        saveButton.addActionListener(this);
        nextButton = new MyButton("next");
        nextButton.addActionListener(this);
        prevButton = new MyButton("prev");
        prevButton.addActionListener(this);
        mainButtonPanel = new JPanel();
        mainButtonPanel.setBackground(Color.WHITE);
        mainButtonPanel.add(prevButton);
        mainButtonPanel.add(nextButton);
        mainButtonPanel.add(saveButton);
        // Menu
        menuBar = new JMenuBar();
        menuBar.setBackground(uni);
        lists = new JMenu("Fragen");
        lists.setBackground(uni);
        lists.setForeground(Color.WHITE);
        stats = new JMenuItem("Statistiken");
        stats.setBackground(uni);
        stats.setForeground(Color.WHITE);
        stats.setMaximumSize(stats.getPreferredSize()); //to prevent the MenuItems fom scaling
        stats.addActionListener(this);
        dailyChallenge = new JMenuItem("Herausforderungen");
        dailyChallenge.setBackground(uni);
        dailyChallenge.setForeground(Color.WHITE);
        dailyChallenge.setMaximumSize(dailyChallenge.getPreferredSize());
        dailyChallenge.addActionListener(this);
        menuBar.add(lists);
            myList = new JMenuItem ("Meine Fragen");
            myList.setBackground(Color.WHITE);
            myList.setForeground(uni);
            myList.addActionListener(this);
            allQuestions = new JMenuItem ("Alle Fragen");
            allQuestions.setBackground(Color.WHITE);
            allQuestions.setForeground(uni);
            allQuestions.addActionListener(this);
            wrongQuestions = new JMenuItem ("Falsche Fragen");
            wrongQuestions.setBackground(Color.WHITE);
            wrongQuestions.setForeground(uni);
            wrongQuestions.addActionListener(this);
        lists.add(myList);
        lists.add(allQuestions);
        lists.add(wrongQuestions);
        menuBar.add(stats);
        menuBar.add(dailyChallenge);
        setJMenuBar(menuBar);
        //Add together
        add(mainQuestionPanel, BorderLayout.CENTER);
        add(mainButtonPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Menu actions
            if (e.getSource().equals(myList)) {
                System.out.println("mylist");
            }
            if (e.getSource().equals(allQuestions)) {
                System.out.println("questions");
            }
            if (e.getSource().equals(wrongQuestions)) {
                System.out.println("wrong");
            }
            if (e.getSource().equals(stats)) {
                System.out.println("stats");
                if (statsWindow == null || !statsWindow.isVisible())
                	statsWindow = new StatsWindow(this);
            }
            if (e.getSource().equals(dailyChallenge)) {
                System.out.println("Dayly");
            }
        // Button Actions
            if (e.getSource().equals(prevButton)) {
                System.out.println("prev");
                String[] answers = {"1", "2", "3", "4"};
                MultipleChoice m = new MultipleChoice("Was ist 2 * 2?", answers, new int[] {3, 4}, 15);
                mainQuestionPanel.showQuestion(m);
            }
            if (e.getSource().equals(nextButton)) {
                System.out.println("next");
                FillBlank f = new FillBlank("Was ist 2 + 2?", "4", 10);
                mainQuestionPanel.showQuestion(f);
            }
            if (e.getSource().equals(saveButton)) {
                System.out.println("safe");
            }
    }

}
