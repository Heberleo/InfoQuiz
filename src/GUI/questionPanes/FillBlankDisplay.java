package GUI.questionPanes;

import Management.*;
import GUI.resources.MyButton;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static GUI.resources.MyColor.uni;


@SuppressWarnings("serial")
public class FillBlankDisplay extends QuestionDisplay implements ActionListener, KeyListener {
    //THE QUESTION!
    private FillBlank question;
    //component declaration
    private JLabel lblQuestion;
    private MyButton btnSubmit;
    private JTextField txtInput;
    private JLabel lblOutput;
    private TimerPanel timer;
    private CardLayout clOutput;
    private JPanel pnlOutput;
    //
    public FillBlankDisplay() {
        GridBagConstraints gbc;
        //the top Panel will contain the Question
        JPanel pnlTop = new JPanel();
        pnlTop.setLayout(new BorderLayout());
        lblQuestion = new JLabel();
        lblQuestion.setFocusable(false);
        lblQuestion.setHorizontalAlignment(JLabel.CENTER);
        lblQuestion.setBackground(Color.WHITE);
        lblQuestion.setOpaque(true);
        lblQuestion.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        Font fontQuestion = lblQuestion.getFont();
        fontQuestion = new Font(fontQuestion.getName(), fontQuestion.getStyle(), 40);
        lblQuestion.setFont(fontQuestion);
        pnlTop.add(lblQuestion, BorderLayout.CENTER);

        //the bottom Panel will a gridLayout containing two panels
        JPanel pnlInput = new JPanel();

        //pnlInput
        pnlInput.setLayout(new GridBagLayout());
        pnlInput.setBackground(Color.WHITE);

        // txtInput
        txtInput = new JTextField();
        txtInput.setBorder(new LineBorder(uni));
        txtInput.setPreferredSize(new Dimension(100, 70));
        txtInput.setFont(new Font(txtInput.getFont().getName(), txtInput.getFont().getStyle(), txtInput.getFont().getSize() * 2));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 15, 0,15);
        pnlInput.add(txtInput, gbc);

        // btnSubmit
        btnSubmit = new MyButton("EINGABE");
        btnSubmit.setPreferredSize(new Dimension(100, 30));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(25, 15, 15,15);
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        gbc.fill = GridBagConstraints.VERTICAL;
        pnlInput.add(btnSubmit, gbc);

        //pnlOutput
        pnlOutput = new JPanel();
        pnlOutput.setVisible(true);
        clOutput = new CardLayout();
        pnlOutput.setLayout(clOutput);
        pnlOutput.setBackground(Color.WHITE);

        // lblOutput
        lblOutput = new JLabel();
        lblOutput.setOpaque(true);
        Font fontOutput = lblOutput.getFont();
        fontOutput = new Font(fontOutput.getName(), fontOutput.getStyle(), fontOutput.getSize() + 15);
        lblOutput.setFont(fontOutput);
        lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
        pnlOutput.add(lblOutput, "lblOutput");

        // timer
        timer = new TimerPanel(this);
        pnlOutput.add(timer, "timer");


        //add to pnlBottom
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(15, 15, 15,15);
        gbc.fill = GridBagConstraints.BOTH;
        pnlInput.add(pnlOutput, gbc);

        //add to the main panel
        setLayout(new GridLayout(2, 1));
        add(pnlTop);
        add(pnlInput);
        setVisible(true);

        //ActionListener
        btnSubmit.addActionListener(this);
        txtInput.addKeyListener(this);
    }

    /////////////////////////////////////////////////////////////////////////////////////
    // methods to interact with the graphical components
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * Checks if the input textfield is empty.
     * @return true if notempty, else false
     */
    private boolean checkEmptyInput() {
        return !getInput().equals("");
    }

    /**
     * Returns the content of the input Textbox.
     * @return input text
     */
    private String getInput() {
        return txtInput.getText();
    }

    /**
     * Enables/ disables the input button and textfield.
     * @param enabled true to enable, false to disable
     */
    private void enableInput(boolean enabled) {
        btnSubmit.setEnabled(enabled);
        if (enabled) txtInput.setText("");
        txtInput.setEnabled(enabled);
    }

    /**
     * Sets the output label to "RICHITG"/ GREEN or "corrextAnswers"/ RED and
     * @param correct true for correct, false for incorrect
     */
    private void setOutput(boolean correct) {
        if (correct) {
            lblOutput.setBackground(Color.GREEN);
            lblOutput.setText("RICHTIG");
        } else {
            lblOutput.setBackground(Color.RED);
            lblOutput.setText("\"" + question.getCorrectAnswer() + "\"");
        }
    }

    /**
     * Sets the question title.
     * @param text the title of the question
     */
    private void setQuestion(String text) { lblQuestion.setText("<html>" + text + "</html>");
    }
    /////////////////////////////////////////////////////////////////////////////////////
    // ActionListeners and KeyListeners
    ////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnSubmit)) {
            if (checkEmptyInput()) {
                hitSubmit();
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == '\n') {
            if (checkEmptyInput()) {
                hitSubmit();
            }
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        //Nothing
    }
    @Override
    public void keyReleased(KeyEvent e) {
        //Nothing
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    // Methods to manage the question
    ////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Displays the question.
     * @param q an object of a Question-subclass
     */
    @Override
    public void readQuestion(Question q) {
        if (!checkType(q)) throw new IllegalQuestionException("FillBlank");
        question = (FillBlank) q;
        setQuestion(question.getTitle());
        clOutput.show(pnlOutput, "timer");
        timer.start(q.getTime());
        enableInput(true);
        txtInput.grabFocus();
    }

    /**
     * Checks, if the argument is of the type FillBlank.
     * @param q the question to be checked.
     * @return true if yes, false if no
     */
    @Override
    public boolean checkType(Question q) {
        return q.getType().equals(Questiontype.FillBlank);
    }
    @Override
    public boolean checkAnswer() {
        return question.getCorrectAnswer().equals(getInput());
    }

    /**
     * This method should be called, when the submit button was clicked/ enter ky was hit. It will check the answer,
     * set the output and update the statistics of the the question and the overall score.
     * Afterwards, the input will be disabled.
     */
    @Override
    public void hitSubmit() {
        timer.stop();
        if (checkAnswer()) {
            setOutput(true);
            question.getStats().increaseCorrectAnswered();
            PointCounter.instance().increasePoints();
        } else {
            setOutput(false);
            question.getStats().increaseWrongAnswered();
            PointCounter.instance().decreasePoints();
        }
        clOutput.show(pnlOutput, "lblOutput");
        enableInput(false);
    }
}
