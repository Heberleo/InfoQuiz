package questions;

public class FillBlank extends Question {
	
	private String correctAnswer;

	public FillBlank(String title, String correctAnswer) {
		this(title, correctAnswer, 20);
	}
	
	public FillBlank(String title, String correctAnswer, int time) {
		super(title, time);
		super.type = Questiontype.FillBlank;
		setCorrectAnswer(correctAnswer);
	}
	
	public String getCorrectAnswer() {
		return this.correctAnswer;
	}
	
	public void setCorrectAnswer(String correctAnswer) throws IllegalArgumentException {
		this.correctAnswer = correctAnswer;
	}
}