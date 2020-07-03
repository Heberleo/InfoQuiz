package EingabeTool;

import Database.AllContainer;
import Database.DBConncetion;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EingabeMain {
	public static void main(String[] args) {
		DBConncetion.connect();
		EingabeWindow test = new EingabeWindow();
		test.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				DBConncetion.closeConnection();
			}
		});
	}
}
