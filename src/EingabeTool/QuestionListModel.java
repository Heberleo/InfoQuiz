package EingabeTool;

import Database.AllContainer;
import Database.DBConncetion;
import Database.DataManagement;
import Database.QuestionImport;
import Management.Question;

import javax.swing.*;

public class QuestionListModel extends DefaultListModel{
	private DataManagement dataManagement = new QuestionImport();

	@Override
	public Object getElementAt(int index) {
		Question q = (Question) super.getElementAt(index);
		return ("(" + q.getType().toString() + ") " + q.getTitle());
	}

	public Question getQuestionAt(int index) {
		Question q = (Question) super.getElementAt(index);
		return q;
	}

	public void updateElement(Question q) {
		// Update in der Datenbank
		dataManagement.edit(q);
		fireContentsChanged(this, q.getId(), q.getId());
	}

	public void newElement(Question q) {
		addElement(q);
		AllContainer.instance().linkQuestion(q);
		dataManagement.add(q);
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
		dataManagement.delete(q);
		super.remove(index);
		super.fireIntervalRemoved(this, index, index);
		return q;
	}

	public void init() {
		QuestionImport questionImport;
		questionImport = new QuestionImport();
		// Datenbank connect
		AllContainer.instance().load();

		//AllContainer.instance.import();
		for(Question q: AllContainer.instance().getList()) {
			addElement(q);
		}
	}
}
