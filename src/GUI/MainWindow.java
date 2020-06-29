package GUI;

import GUI.questionPanes.QuestionPanel;
import Management.MainManagement;
import GUI.resources.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import static GUI.resources.MyColor.uni;

public class MainWindow extends JFrame implements ActionListener {
    private QuestionPanel mainQuestionPanel; //contains the different Question panels;
    private JPanel mainButtonPanel; //contains Buttons
    private MyButton markedButton; //to save question
    private MyButton nextButton; //to get next Question
    private JMenuBar menuBar;
    private JMenu lists; // to navigate different Lists of Questions
        private JMenuItem allQuestions;
        private JMenuItem wrongQuestions;
        private JMenuItem myList;
    private JMenuItem stats; // to go to Stats
    private JMenuItem dailyChallenge; // to Access dailyChallenges
    private StatsWindow statsWindow;
    private WindowListener windowListener;


    public MainWindow() {
        super("InfoQuiz");
        ImageIcon icon = new ImageIcon("lib/images/icon32.png");
        setIconImage(icon.getImage());
        //QuestionPanel
        mainQuestionPanel = new QuestionPanel();
        mainQuestionPanel.setBackground(Color.RED); // Just a Test
        //ButtonPanel
        markedButton = new MyButton("unmarked");
        markedButton.addActionListener(this);
        nextButton = new MyButton("next");
        nextButton.addActionListener(this);
        mainButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        mainButtonPanel.setBackground(Color.WHITE);
        mainButtonPanel.add(nextButton);
        mainButtonPanel.add(markedButton);
        mainButtonPanel.add(nextButton);
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
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(700,700);
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


    @Override
    public void actionPerformed(ActionEvent e) {
        // Menu actions
            if (e.getSource().equals(myList)) {
                System.out.println("mylist");
            }
            if (e.getSource().equals(allQuestions)) {
                System.out.println("Management");
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
                System.out.println("Daily");
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
     * Changes the text/ look of the markedButton, depending of the marked-state of the loaded Question
     * @param marked true for marked, false for unmarked
     */
    private void setMarkedButton(boolean marked) {
        if(marked) {
            markedButton.setText("marked");
        } else {
            markedButton.setText("unmarked");
        }
    }
}
