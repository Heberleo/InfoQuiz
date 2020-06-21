package Management;

public class Stats {
	
	private int correctAnswered = 0;
	private int wrongAnswered = 0;

	public Stats() {
		
	}
	
	public int getCorrectAnswered() {
		return this.correctAnswered;
	}
	
	public void increaseCorrectAnswered() {
		this.correctAnswered++;
	}

	public int getWrongAnswered() {
		return wrongAnswered;
	}
	
	public void increaseWrongAnswered() {
		this.wrongAnswered++;
	}
}