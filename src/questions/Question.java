package questions;

public abstract class Question {
	
	public static final int FILL_BLANK = 1;
	public static final int MULTIPLE_CHOICE = 2;

	private Stats stats;
	private String title;
	private int time; // in seconds
	protected int type;

	public Question(String title, int time) {
		setTitle(title);
		stats = new Stats();
		setTime(time);
	}
	
	public abstract boolean checkAnswer(String answer);

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) throws IllegalArgumentException {
		this.title = title;
	}
	
	public int getType() {
		return this.type;
	}
	
	public int getTime() {
		return this.time;
	}
	
	public void setTime(int time) throws IllegalArgumentException {
		if (this.time < 1)
			throw new IllegalArgumentException("Time must be greater than 0 seconds!");
		this.time = time;
	}
	
	public Stats getStats() {
		return this.stats;
	}
}