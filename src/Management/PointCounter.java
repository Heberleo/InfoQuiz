package Management;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import Database.DataManagement;
import Database.QuestionImport;

public class PointCounter {

	private static PointCounter unique;

	private int points = 0;
	private PropertyChangeSupport pcs;
	
	private DataManagement dataManagement;

	private PointCounter() {
		pcs = new PropertyChangeSupport(this);
		dataManagement = new QuestionImport();
//		setPoints(dataManagement.getScore());
	}

	public static PointCounter instance() {
		if (unique == null)
			unique = new PointCounter();
		return unique;
	}

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

	public void increasePoints() {
		if (this.points < 999)
			setPoints(this.points + 1);
	}

	public void decreasePoints() {
		if (this.points > 0)
			setPoints(this.points - 1);
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
	}
	
	public void savePoints() {
//		dataManagement.saveScore(this.points);
	}
}