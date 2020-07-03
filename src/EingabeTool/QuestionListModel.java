package EingabeTool;

import Database.AllContainer;
import Database.DBConncetion;
import Database.QuestionImport;
import Management.Question;

import javax.swing.*;

public class QuestionListModel extends DefaultListModel{
	@Override
	public Object getElementAt(int index) {
		Question q = (Question) super.getElementAt(index);
		return q.getId() + ". (" + q.getType().toString() + ") " + q.getTitle();
	}

	public Question getQuestionAt(int index) {
		Question q = (Question) super.getElementAt(index);
		return q;
	}

	public void updateElement(Question q) {
		// Update in der Datenbank
		fireContentsChanged(this, q.getId(), q.getId());
	}

	public void newElement(Question q) {
		addElement(q);
		AllContainer.instance().linkQuestion(q);
		fireIntervalAdded(this, q.getId(), q.getId());
	}

	@Override
	public void addElement(Object element) {
		if(!this.contains(element))
		{
			int i=0;
			while(i<this.size()&&((Question)this.get(i)).getId() <= ((Question)element).getId()){
					i++;
				}
				this.add(i, (Question) element);
		}
	}

	@Override
	public Object remove(int index) {
		Question q = (Question) super.getElementAt(index);
		AllContainer.instance().unlinkQuestion(q);
		super.remove(index);
		super.fireIntervalRemoved(this, index, index);
		return q;
	}

	public void init() {
		QuestionImport questionImport;
		questionImport = new QuestionImport();
		// Datenbank connect
		DBConncetion.connect();
		questionImport.addMultipleChoice();
		DBConncetion.closeConnection();

		//AllContainer.instance.import();
		for(Question q: AllContainer.instance().getList()) {
			addElement(q);
		}
	}
}
