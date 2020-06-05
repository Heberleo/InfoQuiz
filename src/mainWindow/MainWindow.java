package mainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PrivateKey;

public class MainWindow extends JFrame implements ActionListener{
    private JPanel mainQuestionPanel; //contains the different Question panels;
    private JPanel mainButtonPanel; //contains Buttons
    private JButton saveButton; //to save question
    private JButton nextButton; //to get next Question
    private JButton prevButton; //to get previous Question
    private JMenuBar menuBar;
    private JMenu lists; // to navigate different Lists of Questions
        private JMenuItem allQuestions;
        private JMenuItem wrongQuestions;
        private JMenuItem myList;
    private JMenuItem stats; // to go to Stats
    private JMenuItem dailyChallenge; // to Access dailyChallenges


    public MainWindow() {
        super("InfoQuiz");
        //QuestionPanel
        mainQuestionPanel = new JPanel();
        mainQuestionPanel.setBackground(Color.RED); // Just a Test
        //ButtonPanel
        saveButton = new JButton("save");
        saveButton.addActionListener(this);
        nextButton = new JButton("next");
        nextButton.addActionListener(this);
        prevButton = new JButton("prev");
        prevButton.addActionListener(this);
        mainButtonPanel = new JPanel();
        mainButtonPanel.add(prevButton);
        mainButtonPanel.add(nextButton);
        mainButtonPanel.add(saveButton);
        // Menu
        menuBar = new JMenuBar();
        lists = new JMenu("Fragen");
        stats = new JMenuItem("Statistiken");
        stats.setMaximumSize(stats.getPreferredSize()); //to prevent the MenuItems fom scaling
        stats.addActionListener(this);
        dailyChallenge = new JMenuItem("Herausforderungen");
        dailyChallenge.setMaximumSize(dailyChallenge.getPreferredSize());
        dailyChallenge.addActionListener(this);
        menuBar.add(lists);
            myList = new JMenuItem ("Meine Fragen");
            myList.addActionListener(this);
            allQuestions = new JMenuItem ("Alle Fragen");
            allQuestions.addActionListener(this);
            wrongQuestions = new JMenuItem ("Falsche Fragen");
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
            }
            if (e.getSource().equals(dailyChallenge)) {
                System.out.println("Dayly");
            }
        // Button Actions
            if (e.getSource().equals(prevButton)) {
                 System.out.println("prev");
            }
            if (e.getSource().equals(nextButton)) {
                System.out.println("next");
            }
            if (e.getSource().equals(saveButton)) {
                System.out.println("safe");
            }
    }

}
