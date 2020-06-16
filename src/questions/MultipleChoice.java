package questions;

public class MultipleChoice extends Question {

	private String[] answers; // length 4
	private int[] correctAnswers;
	
	public MultipleChoice(String title, String[] answers, int[] correctAnswers) {
		this(title, answers, correctAnswers, 20);
	}

	public MultipleChoice(String title, String[] answers, int[] correctAnswers, int time) {
		super(title, time);
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

	public int[] getCorrectAnswers() {
		return this.correctAnswers;
	}

	/**
	 *
	 * @param correctAnswers index starting at 1 and in ascending order!
	 * @throws IllegalArgumentException if null
	 */
	private void setCorrectAnswers(int[] correctAnswers) throws IllegalArgumentException {
		this.correctAnswers = correctAnswers;
	}
}