package Management;

public class MultipleChoice extends Question {

	private String[] answers; // length 4
	private int[] correctAnswers;
	
	public MultipleChoice(String title, String[] answers, int[] correctAnswers, int id) {
		this(title, answers, correctAnswers, 20, id);
	}

	public MultipleChoice(String title, String[] answers, int[] correctAnswers, int time, int id) {
		super(title, time, id);
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