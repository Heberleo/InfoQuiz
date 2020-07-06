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
		GridBagConstraints gbc;

		// the top Panel will contain the Question
		JPanel pnlTop = new JPanel();
		pnlTop.setLayout(new BorderLayout());
		lblQuestion = new JLabel();
		lblQuestion.setFocusable(false);
		lblQuestion.setHorizontalAlignment(JLabel.CENTER);
		lblQuestion.setBackground(Color.WHITE);
		lblQuestion.setOpaque(true);
		Font fontQuestion = lblQuestion.getFont();
		fontQuestion = new Font(fontQuestion.getName(), fontQuestion.getStyle(), 40);
		lblQuestion.setFont(fontQuestion);
		pnlTop.add(lblQuestion, BorderLayout.CENTER);

		// the bottom Panel will a gridLayout containing two panels
		JPanel pnlInput = new JPanel();
		pnlOutput = new JPanel();

			// pnlInput
			pnlInput.setLayout(new GridBagLayout());

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
					cb1.setPreferredSize(new Dimension(300, 40));
					cb2.setPreferredSize(new Dimension(300, 40));
					cb3.setPreferredSize(new Dimension(300, 40));
					cb3.setPreferredSize(new Dimension(300, 40));
					cb1.setMinimumSize(new Dimension(300, 40));
					cb2.setMinimumSize(new Dimension(300, 40));
					cb3.setMinimumSize(new Dimension(300, 40));
					cb3.setMinimumSize(new Dimension(300, 40));
					cb1.setFont(new Font(cb1.getFont().getName(),  cb1.getFont().getStyle(), (int) ( cb1.getFont().getSize() * 1.5)));
					cb2.setFont(new Font(cb2.getFont().getName(),  cb2.getFont().getStyle(), (int) (cb2.getFont().getSize() * 1.5)));
					cb3.setFont(new Font(cb3.getFont().getName(),  cb3.getFont().getStyle(), (int) (cb3.getFont().getSize() * 1.5)));
					cb4.setFont(new Font(cb4.getFont().getName(),  cb4.getFont().getStyle(), (int) (cb4.getFont().getSize() * 1.5)));
					gbc = new GridBagConstraints();
					gbc.gridx = 0;
					gbc.gridy = 0;
					gbc.weightx = 1.0;
					gbc.weighty = 0.0;
					gbc.fill = GridBagConstraints.HORIZONTAL;
					gbc.anchor = GridBagConstraints.WEST;
					gbc.insets = new Insets(0, 15, 0,15);
					pnlInput.add(cb1, gbc);
					gbc.gridx = 1;
					gbc.gridy = 0;
					pnlInput.add(cb2, gbc);
					gbc.gridx = 0;
					gbc.gridy = 1;
					pnlInput.add(cb3, gbc);
					gbc.gridx = 1;
					gbc.gridy = 1;
					pnlInput.add(cb4, gbc);

					// btnSubmit in pnlRight

					btnSubmit = new MyButton("EINGABE");
					btnSubmit.setPreferredSize(new Dimension(100, 30));
					btnSubmit.setMinimumSize(new Dimension(100, 30));
					btnSubmit.setFocusable(false);
					gbc = new GridBagConstraints();
					gbc.insets = new Insets(15, 15, 15,15);
					gbc.gridx = 1;
					gbc.gridy = 2;
					gbc.weightx = 0.0;
					gbc.weighty = 0.0;
					gbc.anchor = GridBagConstraints.LAST_LINE_END;
					gbc.fill = GridBagConstraints.VERTICAL;
					pnlInput.add(btnSubmit, gbc);

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
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.weighty = 1.0;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		pnlInput.add(pnlOutput, gbc);

		// add to the main panel
		setLayout(new GridLayout(2, 1));
		add(pnlTop);
		add(pnlInput);
		setVisible(true);

		// ActionListeners
		btnSubmit.addActionListener(this);

		// backgrounds to White
		setBackground(Color.WHITE);
		pnlInput.setBackground(Color.WHITE);
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
			StringBuilder answer = new StringBuilder("\"");

			StringBuilder correctAnswers = new StringBuilder(question.getCorrectAnswers());
			System.out.println(correctAnswers);
			while (!correctAnswers.toString().equals("")) {
				int i = Integer.parseInt("" + correctAnswers.charAt(0));
				correctAnswers.deleteCharAt(0);
				answer.append(question.getAnswers()[i - 1]);
				answer.append(", ");
			}
			answer = answer.reverse().replace(0,2, "").reverse().append("\"");
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
