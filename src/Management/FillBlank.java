package Management;

public class FillBlank extends Question {
	
	private String correctAnswer;

	public FillBlank(String title, String correctAnswer, int id) {
		this(title, correctAnswer, 20, id);
	}
	
	public FillBlank(String title, String correctAnswer, int time, int id) {
		super(title, time, id);
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