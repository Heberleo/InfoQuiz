package mainWindow.questionPanes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import questions.MultipleChoice;
import questions.Question;

@SuppressWarnings("serial")
public class MultipleChoiceDisplay extends QuestionDisplay implements ActionListener {
	// THE QUESTION!
	private MultipleChoice question;
	// component declaration
	private JTextArea taQuestion;
	private JButton btnSubmit;
	private JCheckBox cb1;
	private JCheckBox cb2;
	private JCheckBox cb3;
	private JCheckBox cb4;
	private JLabel lblOutput;
	private Timer timer;

	public MultipleChoiceDisplay() {
		// the top Panel will contain the Question
		JPanel pnlTop = new JPanel();
		pnlTop.setLayout(new BorderLayout());
		pnlTop.setBackground(Color.GREEN);
		taQuestion = new JTextArea(1, 0);
		taQuestion.setFocusable(false);
		taQuestion.setLineWrap(true);
		taQuestion.setMargin(new Insets(10, 10, 10, 10));
		Font fontQuestion = taQuestion.getFont();
		fontQuestion = new Font(fontQuestion.getName(), fontQuestion.getStyle(), fontQuestion.getSize() + 15);
		taQuestion.setFont(fontQuestion);
		pnlTop.add(taQuestion, BorderLayout.CENTER);
		// the bottom Panel will a gridLayout containing two panels
		JPanel pnlBottom = new JPanel();
		JPanel pnlInput = new JPanel();
		JPanel pnlOutput = new JPanel();
		// pnlInput
		pnlInput.setLayout(new GridLayout(1, 2));
		// CheckBoxes & Labels in pnlLeft
		JPanel pnlLeft = new JPanel();
		pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.Y_AXIS));
		cb1 = new JCheckBox("Antwort 1");
		cb2 = new JCheckBox("Antwort 2");
		cb3 = new JCheckBox("Antwort 3");
		cb4 = new JCheckBox("Antwort 4");
		cb1.setFocusable(false);
		cb2.setFocusable(false);
		cb3.setFocusable(false);
		cb4.setFocusable(false);
		pnlLeft.add(cb1);
		pnlLeft.add(cb2);
		pnlLeft.add(cb3);
		pnlLeft.add(cb4);
		pnlInput.add(pnlLeft);
		// btnSubmit in pnlRight
		JPanel pnlRight = new JPanel(new GridLayout(2, 1));
		pnlRight.setBackground(Color.yellow);
		btnSubmit = new JButton("EINGABE");
		btnSubmit.setFocusable(false);
		btnSubmit.setPreferredSize(new Dimension(100, 40));
		pnlRight.add(btnSubmit);
		timer = new Timer(this, 5);
		pnlRight.add(timer);
		pnlInput.add(pnlRight);
		// pnlOutput
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
		// add to pnlBottom
		pnlBottom.setLayout(new GridLayout(2, 1));
		pnlBottom.add(pnlInput);
		pnlBottom.add(pnlOutput);
		// add to the main panel
		setLayout(new GridLayout(2, 1));
		add(pnlTop);
		add(pnlBottom);
		setVisible(true);
		// ActionListeners
		btnSubmit.addActionListener(this);
	}

	/////////////////////////////////////////////////////////////////////////////////////
	// methods to interact with the graphical components
	/////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Enables/ disables the input button and textfield.
	 * 
	 * @param enabled true to enable, false to disable
	 */
	private void enableInput(boolean enabled) {
		btnSubmit.setEnabled(enabled);
		cb1.setEnabled(enabled);
		cb2.setEnabled(enabled);
		cb3.setEnabled(enabled);
		cb4.setEnabled(enabled);
		if (enabled) {
			cb1.setSelected(false);
			cb2.setSelected(false);
			cb3.setSelected(false);
			cb4.setSelected(false);
		}
	}

	/**
	 * Makes the output label visible/ invisible.
	 * 
	 * @param visible true for visible, false for invisible
	 */
	private void showOutput(boolean visible) {
		lblOutput.setVisible(visible);
	}

	/**
	 * Sets the output label to "RICHITG"/ GREEN or "FALSCH"/ RED
	 * 
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

	private void setAnswers(String[] a) {
		cb1.setText(a[0]);
		cb2.setText(a[1]);
		cb3.setText(a[2]);
		cb4.setText(a[3]);
	}

	private int getSelection() {
		if (cb1.isSelected())
			return 0;
		if (cb2.isSelected())
			return 1;
		if (cb3.isSelected())
			return 2;
		if (cb4.isSelected())
			return 3;
		return -1;
	}

	/**
	 * Sets the question text.
	 * 
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
			timer.start();
			if (getSelection() != -1) {
				// prototype for a method that should be called here
				setOutput(checkAnswer()); // check output with the question
				showOutput(true);
				enableInput(false);
			}
		}
	}
	/////////////////////////////////////////////////////////////////////////////////////////
	// Methods to manage the question
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void readQuestion(Question q) {
		if (!checkType(q))
			throw new IllegalQuestionException("MultipleChoice");
		question = (MultipleChoice) q;
		setQuestion(question.getTitle());
		setAnswers(question.getAnswers());

		showOutput(false);
		enableInput(true);
	}

	@Override
	public boolean checkType(Question q) {
		return q.getClass().equals(MultipleChoice.class);
	}

	@Override
	public boolean checkAnswer() {
		return false;
	}

//    @Override
//    public boolean checkAnswer() {
//        return getSelection() == question.getCorrectAnswer();
//    }
}
