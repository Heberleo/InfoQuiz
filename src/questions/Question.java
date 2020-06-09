package questions;

public abstract class Question {

	private Stats stats;
	private String title;
	private int time; // in seconds
	protected Questiontype type;
	private int id;

	public Question(String title, int time) {
		this(title, time, new Stats());
	}
	
	public Question(String title, int time, Stats stats) {
		setTitle(title);
		setStats(stats);
		setTime(time);
	}
	
	public abstract boolean checkAnswer(String answer);

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
}