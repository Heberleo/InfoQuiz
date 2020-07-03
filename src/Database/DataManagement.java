package Database;

import Management.Question;

public interface DataManagement {
	public void load(AllContainer con);
	public void save(AllContainer con);
	public void delete(Question q , AllContainer con);
}
