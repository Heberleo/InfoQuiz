package Database;

import Management.Category;
import Management.FillBlank;
import Management.MultipleChoice;
import Management.Question;
import Management.Questiontype;

public class QuestionGetter {

	static String[] ans = { "1", "2", "3", "4" };
	static FillBlank q1 = new FillBlank("Hallo1", "1");
	static MultipleChoice q2 = new MultipleChoice("Hallo2", ans, new int[] {2});

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