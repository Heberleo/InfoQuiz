package EingabeTool;

import Database.AllContainer;
import Database.DBConncetion;
import Database.LoadSaveException;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EingabeMain {
	public static void main(String[] args) {
		try {
			DBConncetion.connect();
			EingabeWindow test = new EingabeWindow();
			test.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e)  {
					try {
						DBConncetion.closeConnection();
					} catch (LoadSaveException loadSaveException) {
						loadSaveException.printStackTrace();
					}
				}
			});
		} catch (Exception e ) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Fehler",JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

	}
}
