package mainWindow.questionPanes;

import questions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class FillBlankDisplay extends QuestionDisplay implements ActionListener, KeyListener {
    //THE QUESTION!
    private FillBlank question;
    //component declaration
    private JTextArea taQuestion;
    private JButton btnSubmit;
    private JTextField txtInput;
    private JLabel lblOutput;
    //
    public FillBlankDisplay() {
        //the top Panel will contain the Question
        JPanel pnlTop = new JPanel();
        pnlTop.setLayout(new BorderLayout());
        pnlTop.setBackground(Color.GREEN);
        taQuestion = new JTextArea(1,0);
        taQuestion.setFocusable(false);
        taQuestion.setLineWrap(true);
        taQuestion.setMargin(new Insets(10, 10, 10, 10));
        Font fontQuestion = taQuestion.getFont();
        fontQuestion = new Font(fontQuestion.getName(), fontQuestion.getStyle(), fontQuestion.getSize() + 15);
        taQuestion.setFont(fontQuestion);
        pnlTop.add(taQuestion, BorderLayout.CENTER);
        //the bottom Panel will a gridLayout containing two panels
        JPanel pnlBottom = new JPanel();
        JPanel pnlInput = new JPanel();
        JPanel pnlOutput = new JPanel();
        //pnlInput
        pnlInput.setLayout(new FlowLayout(FlowLayout.RIGHT));
        // txtInput
        txtInput = new JTextField();
        txtInput.setPreferredSize(new Dimension(370, 40));
        pnlInput.add(txtInput);
        // btnSubmit
        btnSubmit = new JButton("EINGABE");
        btnSubmit.setFocusable(false);
        btnSubmit.setPreferredSize(new Dimension(100, 40));
        pnlInput.add(btnSubmit);
        //pnlOutput
        pnlOutput.setLayout(new BorderLayout());
        // lblOutput
        lblOutput = new JLabel();
        lblOutput.setOpaque(true);
        Font fontOutput = lblOutput.getFont();
        fontOutput = new Font(fontOutput.getName(), fontOutput.getStyle(), fontOutput.getSize() + 15);
        lblOutput.setFont(fontOutput);
        lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
        lblOutput.setVisible(false);
        pnlOutput.add(lblOutput, BorderLayout.CENTER);
        //add to pnlBottom
        pnlBottom.setLayout(new GridLayout(2, 1));
        pnlBottom.add(pnlInput);
        pnlBottom.add(pnlOutput);
        //add to the main panel
        setLayout(new GridLayout(2, 1));
        add(pnlTop);
        add(pnlBottom);
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
     * Makes the output label visible/ invisible.
     * @param visible true for visible, false for invisible
     */
    private void showOutput(boolean visible) {
        lblOutput.setVisible(visible);
    }

    /**
     * Sets the output label to "RICHITG"/ GREEN or "FALSCH"/ RED
     * @param correct true for correct, false for incorrect
     */
    private void setOutput(boolean correct) {
        if (correct) {
            lblOutput.setBackground(Color.GREEN);
            lblOutput.setText("RICHTIG");
        } else {
            lblOutput.setBackground(Color.RED);
            lblOutput.setText("FALSCH");
        }
    }

    /**
     * Sets the question text.
     * @param text the question as String
     */
    private void setQuestion(String text) {
        taQuestion.setText(text);
    }
    /////////////////////////////////////////////////////////////////////////////////////
    // ActionListeners and KeyListeners
    ////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnSubmit)) {
            // prototype for a method that should be called here
            if (checkEmptyInput()) {
                setOutput(checkAnswer()); // check output with the question
                showOutput(true);
                enableInput(false);
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == '\n') {
            // prototype for a method that should be called here
            if (checkEmptyInput()) {
                setOutput(checkAnswer()); // check output with the question
                showOutput(true);
                enableInput(false);
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

    @Override
    public void readQuestion(Question q) {
        if (!checkType(q)) throw new IllegalQuestionException("FillBlank");
        question = (FillBlank) q;
        setQuestion(question.getTitle());

        showOutput(false);
        enableInput(true);
    }
    @Override
    public boolean checkType(Question q) {
        return q.getClass().equals(FillBlank.class);
    }
    @Override
    public boolean checkAnswer() {
        return question.getCorrectAnswer().equals(getInput());
    }
}
