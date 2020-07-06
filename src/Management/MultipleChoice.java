package Management;

import java.sql.Statement;

public class MultipleChoice extends Question {

	private String[] answers; // length 4
	private String correctAnswers;
	
	public MultipleChoice(String title, String[] answers, String correctAnswers, int id) {
		this(title, answers, correctAnswers, 20, id, new Stats(0,0),false);
	}

	public MultipleChoice(String title, String[] answers, String correctAnswers, int time, int id, Stats stats, boolean marked) {
		super(title, time,stats,id,marked);
		super.type = Questiontype.MultipleChoice;
		setAnswers(answers);
		setCorrectAnswers(correctAnswers);
	}

	public String[] getAnswers() {
		return this.answers;
	}

	public void setAnswers(String[] answers) throws IllegalArgumentException {
		if (!checkAnswer(answers[0]) || !checkAnswer(answers[1]) || !checkAnswer(answers[2]) || !checkAnswer(answers[3])) throw new IllegalArgumentException("Answers must not be null.");
		this.answers = answers;
	}
	private boolean checkAnswer(String answer) {
		if (answer == null || answer.equals("")) return false;
		return true;
	}

	public String getCorrectAnswers() {
		return this.correctAnswers;
	}

	/**
	 *
	 * @param correctAnswers index starting at 1 and in ascending order!
	 * @throws IllegalArgumentException if null
	 */
	public void setCorrectAnswers(String correctAnswers) throws IllegalArgumentException {
		this.correctAnswers = correctAnswers;
	}

	public String toString() {
		return (this.hashCode() + ", " + super.getTitle() + ", " + getAnswers()[0] + ", " + getAnswers()[1] + ", " + getAnswers()[2] + ", " + getAnswers()[3] + ", " + getCorrectAnswers());
	}

}