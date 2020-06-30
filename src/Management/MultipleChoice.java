package Management;

import java.sql.Statement;

public class MultipleChoice extends Question {

	private String[] answers; // length 4
	private String correctAnswers;
	
	public MultipleChoice(String title, String[] answers, String correctAnswers, int id) {
		this(title, answers, correctAnswers, 20, id,null);
	}

	public MultipleChoice(String title, String[] answers, String correctAnswers, int time, int id, Stats stats) {
		super(title, time,stats,id);

		super.type = Questiontype.MultipleChoice;
		setAnswers(answers);
		setCorrectAnswers(correctAnswers);
	}

	public String[] getAnswers() {
		return this.answers;
	}

	private void setAnswers(String[] answers) throws IllegalArgumentException {
		this.answers = answers;
	}

	public String getCorrectAnswers() {
		return this.correctAnswers;
	}

	/**
	 *
	 * @param correctAnswers index starting at 1 and in ascending order!
	 * @throws IllegalArgumentException if null
	 */
	private void setCorrectAnswers(String correctAnswers) throws IllegalArgumentException {
		this.correctAnswers = correctAnswers;
	}

	public String toString() {
		return (this.hashCode() + ", " + super.getTitle() + ", " + getAnswers()[0] + ", " + getAnswers()[1] + ", " + getAnswers()[2] + ", " + getAnswers()[3] + ", " + getCorrectAnswers());
	}

}