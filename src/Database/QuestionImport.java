package Database;

import Management.MultipleChoice;
import Management.Questiontype;
import Management.Stats;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static Management.Questiontype.FillBlank;
import static Management.Questiontype.MultipleChoice;

public class QuestionImport {
    private Connection c = DBConncetion.getConnection();
    Statement stmt;
    ResultSet rs = null;

    private void addMultipleChoice() {
        try {
            int id;
            String title;
            int time;
            Questiontype type;
            Stats stats;
            String[] answers = new String[4];
            String correctAnswers;
            stmt = c.createStatement();
            rs = stmt.executeQuery("Select * from mainquestion" +
                    "inner join multiplechoice" +
                    " on mainquestion.id = multiplechoice.id");
            while (rs.next()) {
                id = rs.getInt(1);
                title = rs.getString(2);
                time = rs.getInt(3);
                if (rs.getInt(4) == 1) {
                    type = MultipleChoice;
                } else type = FillBlank;
                stats = new Stats(rs.getInt(5),rs.getInt(6));
                for (int i = 0; i < 4; ++i) answers[i] = rs.getString(8 + i);
                correctAnswers = rs.getString(12);
                QuestionContainer.instance().linkQuestion( new MultipleChoice(title,answers,correctAnswers,time,id,stats));
            }
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() +"1" );
            System.exit(0);
        }

    }


}
