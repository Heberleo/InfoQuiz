package questions;

public abstract class Question {

	private String title;

	public Question(String title) {
		setTitle(title);
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) throws IllegalArgumentException {
		this.title = title;
	}
}