package GUI.questionPanes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import Management.MultipleChoice;
import Management.PointCounter;
import Management.Question;
import GUI.resources.MyButton;
import Management.Questiontype;

@SuppressWarnings("serial")
public class MultipleChoiceDisplay extends QuestionDisplay implements ActionListener {
	// THE QUESTION!
	private MultipleChoice question;
	// component declaration
	private JLabel lblQuestion;
	private MyButton btnSubmit;
	private JCheckBox cb1;
	private JCheckBox cb2;
	private JCheckBox cb3;
	private JCheckBox cb4;
	private JLabel lblOutput;
	private TimerPanel timer;
	private CardLayout clOutput;
	private JPanel pnlOutput;
	private JPanel pnlEmpty;

	public MultipleChoiceDisplay() {
		// the top Panel will contain the Question
		JPanel pnlTop = new JPanel();
		pnlTop.setLayout(new BorderLayout());
		lblQuestion = new JLabel();
		lblQuestion.setFocusable(false);
		lblQuestion.setHorizontalAlignment(JLabel.CENTER);
		lblQuestion.setBackground(Color.WHITE);
		lblQuestion.setOpaque(true);
		Font fontQuestion = lblQuestion.getFont();
		fontQuestion = new Font(fontQuestion.getName(), fontQuestion.getStyle(), fontQuestion.getSize() + 15);
		lblQuestion.setFont(fontQuestion);
		pnlTop.add(lblQuestion, BorderLayout.CENTER);

		// the bottom Panel will a gridLayout containing two panels
		JPanel pnlBottom = new JPanel();
		JPanel pnlInput = new JPanel();
		pnlOutput = new JPanel();

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
					cb1.setBackground(Color.WHITE);
					cb2.setBackground(Color.WHITE);
					cb3.setBackground(Color.WHITE);
					cb4.setBackground(Color.WHITE);
					pnlLeft.add(cb1);
					pnlLeft.add(cb2);
					pnlLeft.add(cb3);
					pnlLeft.add(cb4);
					pnlInput.add(pnlLeft);

					// btnSubmit in pnlRight
					JPanel pnlRight = new JPanel(new GridLayout(2, 1));
					pnlRight.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
					btnSubmit = new MyButton("EINGABE");
					btnSubmit.setFocusable(false);
					btnSubmit.setPreferredSize(new Dimension(100, 40));
					pnlRight.add(btnSubmit);
					pnlInput.add(pnlRight);

			//pnlOutput
			clOutput = new CardLayout();
			pnlOutput.setLayout(clOutput);

				// lblOutput
				lblOutput = new JLabel();
				lblOutput.setOpaque(true);
				Font fontOutput = lblOutput.getFont();
				fontOutput = new Font(fontOutput.getName(), fontOutput.getStyle(), fontOutput.getSize() + 15);
				lblOutput.setFont(fontOutput);
				lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
				pnlOutput.add(lblOutput, "lblOutput");

				//timer
				timer = new TimerPanel(this);
				pnlOutput.add(timer, "timer");

				// pnlEmpty
				pnlEmpty = new JPanel();
				pnlEmpty.setBackground(Color.WHITE);
				pnlOutput.add(pnlEmpty, "empty");

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

		// backgrounds to White
		setBackground(Color.WHITE);
		pnlBottom.setBackground(Color.WHITE);
		pnlInput.setBackground(Color.WHITE);
		pnlLeft.setBackground(Color.WHITE);
		pnlRight.setBackground(Color.WHITE);
		pnlTop.setBackground(Color.WHITE);
		pnlOutput.setBackground(Color.WHITE);
	}

	/////////////////////////////////////////////////////////////////////////////////////
	// methods to interact with the graphical component
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
	 * 
	 * @param correct true for correct, false for incorrect
	 */
	private void setOutput(boolean correct) {
		if (correct) {
			lblOutput.setBackground(Color.GREEN);
			lblOutput.setText("RICHTIG");
		} else {
			lblOutput.setBackground(Color.RED);
			StringBuilder answer = new StringBuilder("Richtige Antworten: ");
			answer.append(question.getCorrectAnswers());
			lblOutput.setText(answer.toString());
		}
	}

	/**
	 * Sets the answeroptions
	 * @param a answers
	 */
	private void setAnswers(String[] a) {
		cb1.setText(a[0]);
		cb2.setText(a[1]);
		cb3.setText(a[2]);
		cb4.setText(a[3]);
	}

	/**
	 * Index will start at 1 and in ascending order, as String
	 * @return null if nothing is selected
	 */
	private String getSelection() {
		StringBuilder selection = new StringBuilder();
		if (cb1.isSelected())
			selection.append(1);
		if (cb2.isSelected())
			selection.append(2);
		if (cb3.isSelected())
			selection.append(3);
		if (cb4.isSelected())
			selection.append(4);
		if (selection.equals("")) return null;
		return  selection.toString();
	}

	/**
	 * Checks if the selection is empty.
	 * @return true if no answer is selected
	 */
	private boolean selectionIsEmpty() {
		if(getSelection().equals(null)) return true;
		return false;
	}
	/**
	 * Sets the question text.
	 * 
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
			if (!selectionIsEmpty())
				hitSubmit();
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

		showTimer(true);
		enableInput(true);
	}

	@Override
	public boolean checkType(Question q) {
		return q.getType().equals(Questiontype.MultipleChoice);
	}


    @Override
    public boolean checkAnswer() {
		return question.getCorrectAnswers().equals(getSelection());
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
