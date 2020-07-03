package Management;

public class FillBlank extends Question {
	
	private String correctAnswer;

	public FillBlank(String title, String correctAnswer, int id) {
		this(title, correctAnswer, 20, id, null);
	}
	
	public FillBlank(String title, String correctAnswer, int time, int id, Stats stats) {
		super(title, time,stats, id);
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