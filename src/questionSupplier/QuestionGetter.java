package questionSupplier;

import questions.Category;
import questions.FillBlank;
import questions.MultipleChoice;
import questions.Question;
import questions.Questiontype;

public class QuestionGetter {

	static String[] ans = { "1", "2", "3", "4" };
	static FillBlank q1 = new FillBlank("Hallo1", "1");
	static MultipleChoice q2 = new MultipleChoice("Hallo2", ans, 2);

	public static Question getRandomQuestion() {
		return Math.random() > 0.5 ? q1 : q2;
	}
	
	public static Question getRandomQuestion(Questiontype type) {
		Question q;
		do {
			q = getRandomQuestion();
		} while (q.getType() != type);
		return q;
	}

	public static Question getRandomQuestion(Category cat) throws IllegalArgumentException {
		if (!Category.asList().contains(cat))
			throw new IllegalArgumentException("Category does not exist!");
		return null;
	}
}