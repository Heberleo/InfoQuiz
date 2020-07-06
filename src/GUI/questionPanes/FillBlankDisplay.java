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
    private JPanel pnlEmpty;
    //
    public FillBlankDisplay() {
        //the top Panel will contain the Question
        JPanel pnlTop = new JPanel();
        pnlTop.setLayout(new BorderLayout());
        pnlTop.setBackground(Color.GREEN);
        lblQuestion = new JLabel();
        lblQuestion.setFocusable(false);
        lblQuestion.setHorizontalAlignment(JLabel.CENTER);
        lblQuestion.setBackground(Color.WHITE);
        lblQuestion.setOpaque(true);
        Font fontQuestion = lblQuestion.getFont();
        fontQuestion = new Font(fontQuestion.getName(), fontQuestion.getStyle(), fontQuestion.getSize() + 15);
        lblQuestion.setFont(fontQuestion);
        pnlTop.add(lblQuestion, BorderLayout.CENTER);

        //the bottom Panel will a gridLayout containing two panels
        JPanel pnlBottom = new JPanel();
        JPanel pnlInput = new JPanel();
        pnlOutput = new JPanel();

        //pnlInput
        pnlInput.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlInput.setBackground(Color.WHITE);

        // txtInput
        txtInput = new JTextField();
        txtInput.setBorder(new LineBorder(uni));
        txtInput.setPreferredSize(new Dimension(370, 40));
        pnlInput.add(txtInput);

        // btnSubmit
        btnSubmit = new MyButton("EINGABE");
        btnSubmit.setPreferredSize(new Dimension(100, 40));
        pnlInput.add(btnSubmit);

        //pnlOutput
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
        lblOutput.setVisible(false);
        pnlOutput.add(lblOutput, "lblOutput");

        // timer
        timer = new TimerPanel(this);
        pnlOutput.add(timer, "timer");

        // pnlEmpty
        pnlEmpty = new JPanel();
        pnlEmpty.setBackground(Color.WHITE);
        pnlOutput.add(pnlEmpty, "empty");

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
        if (visible) {
            clOutput.show(pnlOutput, "lblOutput");
        } else {
            clOutput.show(pnlOutput, "empty");
        }
    }

    /**
     * Selects timer in the CardLayout and starts the timer.
     * @param visible true makes timer active + visible
     */
    private void showTimer(boolean visible) {
        if (visible) {
            timer.start(question.getTime());
            clOutput.show(pnlOutput, "timer");
        } else {
            timer.stop();
            clOutput.show(pnlOutput, "empty");
        }
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
            lblOutput.setText("Richtige Antwort: " + question.getCorrectAnswer());
        }
    }

    /**
     * Sets the question text.
     * @param text the question as String
     */
    private void setQuestion(String text) {
        lblQuestion.setText(text);
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

    @Override
    public void readQuestion(Question q) {
        if (!checkType(q)) throw new IllegalQuestionException("FillBlank");
        question = (FillBlank) q;
        setQuestion(question.getTitle());

        showTimer(true);
        enableInput(true);
        txtInput.grabFocus();
    }
    @Override
    public boolean checkType(Question q) {
        return q.getType().equals(Questiontype.FillBlank);
    }
    @Override
    public boolean checkAnswer() {
        return question.getCorrectAnswer().equals(getInput());
    }

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
        showOutput(true);
        enableInput(false);
    }
}
