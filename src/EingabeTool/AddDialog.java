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
				if(getTime() == 30) {
					if (!(JOptionPane.showConfirmDialog(owner1, "Bestätige Zeit: 30s", "", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)) return;
				}
					try {
						Question q;
						if(getQuestionType().equals(Questiontype.FillBlank)) {
							q = new FillBlank(getQuestionTitle(), getCorrectAnswer(), getTime(), AllContainer.instance().getNextID(), new Stats(0,0),false);
						} else {
							q = new MultipleChoice(getQuestionTitle(), getAnswers(), getCorrectAnswer(), getTime(), AllContainer.instance().getNextID(), new Stats(0,0),false);
						}
						model.newElement(q);
						dispose();
					} catch (Exception ee) {
						JOptionPane.showMessageDialog(null,ee.getMessage(),"Fehler",JOptionPane.ERROR_MESSAGE);
					}
			}
		});
	}
}
