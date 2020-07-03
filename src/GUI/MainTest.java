package GUI;

import Database.AllContainer;
import Database.DBConncetion;
import Database.QuestionImport;
import Management.MainManagement;
import Management.Question;
import Management.Stats;

import java.sql.Connection;
import java.sql.Connection;
import java.sql.Statement;


public class MainTest {
    public static void main(String[] args) {
        MainManagement.init();
        for (Question x: AllContainer.instance().getList()) System.out.println(x.getTitle());
        MainWindow m = new MainWindow();
    }
}


