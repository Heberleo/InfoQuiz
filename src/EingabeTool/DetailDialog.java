package EingabeTool;

import Management.FillBlank;
import Management.MultipleChoice;
import Management.Question;
import Management.Questiontype;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailDialog extends AbstractDialog {
	public DetailDialog(Frame owner, QuestionListModel model, Question q) {
		super(owner);
		setTitle("Details");
		setButton("Edit");
		enableType(false);
		showQuestion(q);
		enableAll(false);
		addButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isOpen()) {
					setButton("Confirm");
					enableAll(true);
				} else {
					q.setTime(getTime());
					q.setTitle(getQuestionTitle());
					if (q.getType().equals(Questiontype.FillBlank)) {
						((FillBlank) q).setCorrectAnswer(getCorrectAnswer());
					} else {
						((MultipleChoice) q).setAnswers(getAnswers());
						((MultipleChoice) q).setCorrectAnswers(getCorrectAnswer());
					}
					model.updateElement(q);
					setButton("Edit");
					enableAll(false);
				}
			}
		});
	}
}
