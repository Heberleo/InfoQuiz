package questions;

public class MultipleChoice extends Question {

	private String[] answers; // length 4
	private int correctAnswer; // between 0 and 3

	public MultipleChoice(String title, String[] answers, int correctAnswer) {
		super(title);
		setAnswers(answers);
		setCorrectAnswer(correctAnswer);
	}

	public String[] getAnswers() {
		return this.answers;
	}

	private void setAnswers(String[] answers) throws IllegalArgumentException {
		this.answers = answers;
	}

	public int getCorrectAnswer() {
		return this.correctAnswer;
	}

	private void setCorrectAnswer(int correctAnswer) throws IllegalArgumentException {
		this.correctAnswer = correctAnswer;
	}
}