package Database;

import GUI.questionPanes.IllegalQuestionException;
import Management.MultipleChoice;
import Management.Question;

import java.util.ArrayList;
import java.util.Random;

public class MarkedContainer {
	private static MarkedContainer unique;
	public ArrayList<Question> list;
	Random randy;


	private MarkedContainer() {
		list = new ArrayList<Question>();
		randy = new Random();
	}

	public static MarkedContainer instance() {
		if (unique == null) unique = new MarkedContainer();
		return unique;
	}

	public void linkQuestion(Question q) {
		//Exception
		if (list.contains(q)) throw new IllegalQuestionException("Doppelte Frage");
		list.add(q);
	}

	public void unlinkQuestion(Question q) {
		//Exception
		list.remove(q);
	}

	public Question next() {
		if (list.isEmpty())
			return null;
		int id = randy.nextInt(list.size());
		return list.get(id);
	}

	public ArrayList<Question> getList() {
		return list;
	}
}
