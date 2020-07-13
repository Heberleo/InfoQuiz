package Database;

import Management.Question;

public interface DataManagement {
	public void load(AllContainer con) throws LoadSaveException;
	public void save(AllContainer con) throws LoadSaveException;
	public void delete(Question q) throws LoadSaveException;
	public void add(Question q) throws LoadSaveException;
	public void edit(Question q) throws LoadSaveException;
	public void saveScore(int score) throws LoadSaveException;
	public int  getScore() throws LoadSaveException;
}
