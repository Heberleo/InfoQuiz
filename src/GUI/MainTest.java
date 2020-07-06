package GUI;

import Database.AllContainer;
import Database.DBConncetion;
import Database.QuestionImport;
import Management.*;

import java.sql.Connection;
import java.sql.Connection;
import java.sql.Statement;



public class MainTest {
    public static void main(String[] args) {
        MainManagement.init();
        //for (Question x: AllContainer.instance().getList()) System.out.println(x.getTitle());
        //AllContainer.instance().next().hitMarked();
        QuestionImport q = new QuestionImport();
        DBConncetion.connect();
        q.saveScore(1337);
        q.getScore();
        //String[] answers = {"1","2","3","4"};
        //MultipleChoice mc = new MultipleChoice("Was ist 2*2 ?",answers,"4",60,8,new Stats(0,0),false);
        //FillBlank fb = new FillBlank("Hallo ?","welt",60,7,new Stats(0,0),false);
        //q.add(mc);
        DBConncetion.closeConnection();
        MainWindow m = new MainWindow();
    }
}


