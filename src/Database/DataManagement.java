package Database;

import Management.Question;

public interface DataManagement {
	public void load(AllContainer con);
	public void save(AllContainer con);
	public void delete(Question q);
	public void add(Question q);
	public void edit(Question q);
}
