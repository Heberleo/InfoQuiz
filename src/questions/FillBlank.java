package questions;

public class FillBlank extends Question {
	
	private String correctAnswer;

	public FillBlank(String title, String correctAnswer) {
		this(title, correctAnswer, 20);
	}
	
	public FillBlank(String title, String correctAnswer, int time) {
		super(title, time);
		super.type = Question.FILL_BLANK;
		setCorrectAnswer(correctAnswer);
	}
	
	@Override
	public boolean checkAnswer(String answer) {
		return answer == this.correctAnswer;
	}
	
	public String getCorrectAnswer() {
		return this.correctAnswer;
	}
	
	public void setCorrectAnswer(String correctAnswer) throws IllegalArgumentException {
		this.correctAnswer = correctAnswer;
	}
}