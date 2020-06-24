package Management;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Question {

	private Stats stats;
	private String title;
	private int time; // in seconds
	protected Questiontype type;
	private int id;
	private boolean marked;

	public Question(String title, int time, int id) {
		this(title, time, new Stats(0,0), id);
	}
	
	public Question(String title, int time, Stats stats, int id) {
		setTitle(title);
		setStats(stats);
		setTime(time);
		setId(id);
		setMarked(false); // needs to be added as argument
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) throws IllegalArgumentException {
		this.title = title;
	}
	
	public Questiontype getType() {
		return this.type;
	}
	
	public int getTime() {
		return this.time;
	}
	
	public void setTime(int time) throws IllegalArgumentException {
		if (time < 1)
			throw new IllegalArgumentException("Time must be greater than 0 seconds!");
		this.time = time;
	}
	
	public Stats getStats() {
		return this.stats;
	}
	
	public void setStats(Stats stats) {
		this.stats = stats;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void hitMarked() {
		marked = !marked;
	}

	public boolean isMarked() {
		return marked;
	}

	private void setMarked(boolean marked) {
		this.marked = marked;
	}
}