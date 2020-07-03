package Management;

public enum QuestionList {
	ALL, MARKED;

	@Override
	public String toString() {
		if (this == ALL) return "Alle Fragen";
		else return "Markierte Fragen";
	}
}
