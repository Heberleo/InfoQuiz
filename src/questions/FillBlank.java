package questions;

public class FillBlank extends Question {
	
	private String correctAnswer;

	public FillBlank(String title, String correctAnswer) {
		super(title);
		setCorrectAnswer(correctAnswer);
	}
	
	public String getCorrectAnswer() {
		return this.correctAnswer;
	}
	
	public void setCorrectAnswer(String correctAnswer) throws IllegalArgumentException {
		this.correctAnswer = correctAnswer;
	}
}