package EingabeTool;

import Database.AllContainer;
import Management.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDialog extends AbstractDialog {

	public AddDialog(Frame owner, QuestionListModel model) {
		super(owner);
		JDialog owner1 = this;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Add question");
		setButton("Confirm");
		addButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					try {
						Question q;
						if(getQuestionType().equals(Questiontype.FillBlank)) {
							q = new FillBlank(getQuestionTitle(), getCorrectAnswer(), getTime(), AllContainer.instance().getNextID());
						} else {
							q = new MultipleChoice(getQuestionTitle(), getAnswers(), getCorrectAnswer(), getTime(), AllContainer.instance().getNextID(), new Stats(0,0));
						}
						model.newElement(q);
						dispose();
					} catch (Exception ee) {
						JOptionPane.showMessageDialog(owner1, "Please check your input.");
					}
			}
		});
	}
}
