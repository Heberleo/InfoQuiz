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
		if (!checkAnswer(answers[0]) || !checkAnswer(answers[1]) || !checkAnswer(answers[2]) || !checkAnswer(answers[3])) throw new IllegalArgumentException("Antworten dürfen nicht leer sein.");
		if (!checkLength(answers[0]) || !checkLength(answers[1]) || !checkLength(answers[2]) || !checkLength(answers[3])) throw new IllegalArgumentException("Antworten dürfen maximal 30 Zeichen lang sein.");
		this.answers = answers;
	}
	private boolean checkAnswer(String answer) {
		if (answer == null || answer.equals("")) return false;
		return true;
	}
	private boolean checkLength(String answer) {
		if (answer.length() > 30) return false;
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
		if (!checkCorrectAnswers(correctAnswers)) throw new IllegalArgumentException("Richtige Antworten");
		this.correctAnswers = correctAnswers;
	}

	private boolean checkCorrectAnswers(String s) {
		if (s == null || s.equals("")) return false;
		if (s.equals("1") || s.equals("12") || s.equals("123") || s.equals("1234") || s.equals("13") ||s.equals("134") ||s.equals("14") ||s.equals("2") || s.equals("23") || s.equals("234") || s.equals("24") || s.equals("3") || s.equals("34") || s.equals("4")) return true;
		return false;
	}
}