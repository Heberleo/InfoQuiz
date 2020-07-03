package Database;

import Management.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static Management.Questiontype.FillBlank;
import static Management.Questiontype.MultipleChoice;

public class QuestionImport implements DataManagement {
    private Connection c;
    private Statement stmt;
    private ResultSet rs = null;


    @Override
    public void load(AllContainer con) {
        addMultipleChoice(con);
        addFillBlank(con);
    }

    @Override
    public void save(AllContainer con) {
        try {
            c = DBConncetion.getConnection();
            String sql = "Update mainquestion \n" +
                    "Set answered_correct = ? , answered_incorrect = ?\n" +
                    "Where id = ?";
            PreparedStatement pstmt = c.prepareStatement(sql);
            for (Question q : con.getList()) {
                Stats stats = q.getStats();
                System.out.println(stats.getCorrectAnswered());
                pstmt.setString(1,String.valueOf(stats.getCorrectAnswered()));
                pstmt.setString(2,String.valueOf(stats.getWrongAnswered()));
                pstmt.setString(3,String.valueOf(q.getId()));
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() + "1" );
            e.printStackTrace();
            System.exit(0);
        }
    }

    @Override
    public void delete(Question q, AllContainer con) {
        try {
            c = DBConncetion.getConnection();
            String sql = "Update mainquestion \n" +
                    "Set title = null  , type = null , time = null, answered_correct = null, answered_incorrect = null \n" +
                    "Where id = ?";
            PreparedStatement pstmt = c.prepareStatement(sql);
                pstmt.setString(1,String.valueOf(q.getId()));
                pstmt.executeUpdate();
            if (q.getType() == FillBlank) {
                pstmt = c.prepareStatement("Delete From fill_blank \n" +
                        "Where id = ? ");
                pstmt.setString(1,String.valueOf(q.getId()));
                pstmt.executeUpdate();
            } else {
                pstmt = c.prepareStatement("Delete From multiplechoice \n" +
                        "Where id = ? ");
                pstmt.setString(1,String.valueOf(q.getId()));
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() + "1" );
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void addMultipleChoice(AllContainer con) {
        try {
            c = DBConncetion.getConnection();
            int id;
            String title;
            int time;
            Questiontype type;
            Stats stats;
            String[] answers;
            String correctAnswers;
            stmt = c.createStatement();
            rs = stmt.executeQuery("Select * from mainquestion" +
                    " inner join multiplechoice" +
                    " on mainquestion.id = multiplechoice.id");
            while (rs.next()) {
                answers = new String[4];
                id = rs.getInt("id");
                title = rs.getString("title");
                time = rs.getInt("time");
                type = MultipleChoice;
                stats = new Stats(rs.getInt("answered_correct"),rs.getInt("answered_incorrect"));
                for (int i = 0; i < 4; ++i) answers[i] = rs.getString(8 + i);
                correctAnswers = rs.getString(12);
                con.linkQuestion( new MultipleChoice(title,answers,correctAnswers,time,id,stats));
            }
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() + "1" );
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void addFillBlank(AllContainer con) {
        try {
            c = DBConncetion.getConnection();
            int id;
            String title;
            int time;
            Questiontype type;
            Stats stats;
            String answer;
            stmt = c.createStatement();
            rs = stmt.executeQuery("Select * from mainquestion" +
                    " inner join fill_blank" +
                    " on mainquestion.id = fill_blank.id");
            while (rs.next()) {
                answer = new String();
                id = rs.getInt("id");
                title = rs.getString("title");
                time = rs.getInt("time");
                type = FillBlank;
                stats = new Stats(rs.getInt("answered_correct"),rs.getInt("answered_incorrect"));
                answer = rs.getString("answer");
                con.linkQuestion(new FillBlank(title,answer,time,id,stats));
            }
    } catch (Exception e) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() + "2" );
        e.printStackTrace();
        System.exit(0);
    }
    }
}
