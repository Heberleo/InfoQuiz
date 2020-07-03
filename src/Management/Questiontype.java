package Management;

public enum Questiontype {
	
	MultipleChoice, FillBlank //Muliple Coice == 1 in DB Fillblank == 2
	;


	@Override
	public String toString() {
		if(this == MultipleChoice) return "MultipleChoice";
		return "FillBlank";
	}
}
