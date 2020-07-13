package EingabeTool;

import Database.AllContainer;
import Database.DataManagement;
import Database.LoadSaveException;
import Database.QuestionImport;
import Management.PointCounter;
import Management.Question;
import Management.Stats;

import javax.swing.*;


public class QuestionListModel extends DefaultListModel<Question>{
	private DataManagement dataManagement = new QuestionImport();

	/**
	 * @param index of the Element
	 * @return a String representation of the Question at the given index in format: ("type")"title"
	 */
	@Override
	public Question getElementAt(int index) {
		return super.getElementAt(index);
	}


	/**
	 * Sends the edited question to the database to be saved.
	 * @param q the question that should be saved.
	 */
	public void updateElement(Question q) throws LoadSaveException {
		// Update in der Datenbank
		dataManagement.edit(q);
		fireContentsChanged(this, q.getId(), q.getId());
	}

	/**
	 * Adds a new question to the model and saves ist automatically in the database.
	 * @param q new question
	 */
	public void newElement(Question q) throws LoadSaveException {
		addElement(q);
		AllContainer.instance().linkQuestion(q);
		dataManagement.add(q);
		fireIntervalAdded(this, q.getId(), q.getId());
	}

	@Override
	public void addElement(Question element) {
		if(!this.contains(element))
		{
			int i=0;
			while(i<this.size()&&(this.get(i)).getId() <= (element).getId()){
					i++;
				}
				this.add(i, element);
		}
	}

	/**
	 * Removes a question from the model and automatically deletes it from the database.
	 * @param index Index of the question to be removed.
	 * @return the question wich was removed.
	 */
	@Override
	public Question remove(int index) {
		Question q = (Question) super.getElementAt(index);
		AllContainer.instance().unlinkQuestion(q);
		try {
			dataManagement.delete(q);
		} catch (LoadSaveException e) {
			e.printStackTrace();
		}
		super.remove(index);
		super.fireIntervalRemoved(this, index, index);
		return q;
	}

	/**
	 * Initializes the QuestionListModel. Imports all questions from the database.
	 */
	public void init() throws LoadSaveException {
		// Datenbank connect
		AllContainer.instance().load();

		//AllContainer.instance.import();
		for(Question q: AllContainer.instance().getList()) {
			addElement(q);
		}
	}

	/**
	 * Iterates over all Question and sets the stats to 0, 0 and the marked-status to false. Automatically saves changes
	 * to the database.
	 */
	public void resetStats() throws LoadSaveException {
		for (Question q : AllContainer.instance().getList()) {
			q.setStats(new Stats(0,0));
			q.setMarked(false);
		}
		dataManagement.save(AllContainer.instance());
	}

	/**
	 * Sets the score to 0 and saves it.
	 */
	public void resetScore() throws LoadSaveException {
		PointCounter.instance().load();
		PointCounter.instance().setPoints(0);
		PointCounter.instance().savePoints();
	}
}
