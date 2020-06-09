package questions;

import java.util.ArrayList;
import java.util.Arrays;

public enum Category {

	programmingC, programmingJava, uml;

	public static ArrayList<Category> asList() {
		return new ArrayList<Category>(Arrays.asList(Category.values()));
	}
}