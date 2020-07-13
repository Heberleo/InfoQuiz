package Management;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import Database.DataManagement;
import Database.LoadSaveException;
import Database.QuestionImport;

public class PointCounter {

	private static PointCounter unique;

	private int points = 0;
	private PropertyChangeSupport pcs;

	private DataManagement dataManagement;

	private PointCounter() {
		pcs = new PropertyChangeSupport(this);
	}

	/**
	 * @return unique instance of PointCounter (Singleton pattern)
	 */
	public static PointCounter instance() {
		if (unique == null)
			unique = new PointCounter();
		return unique;
	}

	/**
	 * loads the score from database
	 */
	public void load() throws LoadSaveException {
		dataManagement = new QuestionImport();
		setPoints(dataManagement.getScore());
	}

	/**
	 * @return current points
	 */
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		if (points >= 0) {
			int old = this.points;
			this.points = points;
			pcs.firePropertyChange("points", old, this.points);
		}
	}

	/**
	 * increases the points if a question was answered correct
	 */
	public void increasePoints() {
		if (this.points < 9999)
			setPoints(this.points + 1);
	}

	/**
	 * decreases the points if a question was answered wrong
	 */
	public void decreasePoints() {
		if (this.points > 0)
			setPoints(this.points - 1);
	}

	/**
	 * adds PropertyChangeListener to the PointCounter
	 * @param l PropertyChangeListener to add to the PropertyChangeSupport
	 */
	public void addPropertyChangeListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
	}

	/**
	 * saves the current score in the database
	 */
	public void savePoints() throws LoadSaveException {
		dataManagement.saveScore(this.points);
	}
}