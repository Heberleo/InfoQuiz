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
		setButton("Bearbeiten");
		enableType(false);
		showQuestion(q);
		enableAll(false);
		JDialog owner1 = this;
		addButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isOpen()) {
					setButton("Bestätigen");
					enableAll(true);
				} else {
					try {
						q.setTime(getTime());
						q.setTitle(getQuestionTitle());
						if (q.getType().equals(Questiontype.FillBlank)) {
							((FillBlank) q).setCorrectAnswer(getCorrectAnswer());
						} else {
							((MultipleChoice) q).setAnswers(getAnswers());
							((MultipleChoice) q).setCorrectAnswers(getCorrectAnswer());
						}
						model.updateElement(q);
						setButton("Bearbeiten");
						enableAll(false);
					} catch (Exception ee) {
						JOptionPane.showMessageDialog(owner1, "Fehler: " + ee.getMessage());
					}
				}
			}
		});
	}
}
