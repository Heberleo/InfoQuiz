package Database;

import Management.*;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static Management.Questiontype.FillBlank;
import static Management.Questiontype.MultipleChoice;

public class QuestionImport implements DataManagement {
    private Connection c;
    private Statement stmt;
    private ResultSet rs = null;

    /**
     * loads all questions into the Allcontainer
     * @param con the Allcontainer
     */
    @Override
    public void load(AllContainer con) {
        addMultipleChoice(con);
        addFillBlank(con);
    }

    /**
     * updates the stats and the marked attribute
     * @param con the Allcontainer
     */
    @Override
    public void save(AllContainer con) {
        try {
            c = DBConncetion.getConnection();
            String sql = "Update mainquestion \n" +
                    "Set answered_correct = ? , answered_incorrect = ?\n , marked = ?" +
                    "Where id = ?";
            PreparedStatement pstmt = c.prepareStatement(sql);
            for (Question q : con.getList()) {
                int marked = q.isMarked() ? 1 : 0;
                Stats stats = q.getStats();
                System.out.println(stats.getCorrectAnswered());
                pstmt.setString(1,String.valueOf(stats.getCorrectAnswered()));
                pstmt.setString(2,String.valueOf(stats.getWrongAnswered()));
                pstmt.setString(3,String.valueOf(marked));
                pstmt.setString(4,String.valueOf(q.getId()));
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() + "1" );
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * deletes the question q from the Database
     * Deleted id's won't be reassigned
     * @param q
     */
    @Override
    public void delete(Question q) {
        try {
            c = DBConncetion.getConnection();
            String sql = "Delete From mainquestion where id = ?";
            PreparedStatement pstmt = c.prepareStatement(sql);
                pstmt.setString(1,String.valueOf(q.getId()));
                pstmt.executeUpdate();
            if (q.getType() == FillBlank) {
                pstmt = c.prepareStatement("Delete From fill_blank \n" +
                        "Where id = ? ");
            } else {
                pstmt = c.prepareStatement("Delete From multiplechoice \n" +
                        "Where id = ? ");
            }
            pstmt.setString(1,String.valueOf(q.getId()));
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() + "1" );
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * adds the Question q to the database
     * @param q
     */
    @Override
    public void add(Question q) {
        try {
            c = DBConncetion.getConnection();
            int type = q.getType() == MultipleChoice ? 1 : 2; // 1 = MC , 2 = FB
            System.out.println(type);
            String sql = "Insert into mainquestion \n" +
                    "Values (?,?,?,?,0,0,0)";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1,String.valueOf(q.getId()));
            pstmt.setString(2,q.getTitle());
            pstmt.setString(3,String.valueOf(q.getTime()));
            pstmt.setString(4,String.valueOf(type));
            pstmt.executeUpdate();
            if (type == 1) {
                sql = "Insert into multiplechoice \n" +
                       "Values (?,?,?,?,?,?)";
                MultipleChoice mc = (MultipleChoice) q;
                String[] answers = mc.getAnswers();
                pstmt = c.prepareStatement(sql);
                pstmt.setString(1,String.valueOf(q.getId()));
                pstmt.setString(2,answers[0]);
                pstmt.setString(3,answers[1]);
                pstmt.setString(4,answers[2]);
                pstmt.setString(5,answers[3]);
                pstmt.setString(6,mc.getCorrectAnswers());
                pstmt.executeUpdate();
            } else {
                sql = "Insert into fill_blank Values(?,?)";
                FillBlank fb = (FillBlank) q;
                pstmt = c.prepareStatement(sql);
                pstmt.setString(1,String.valueOf(q.getId()));
                pstmt.setString(2,fb.getCorrectAnswer());
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() + "1" );
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Edits a question q
     * @param q
     */
    @Override
    public void edit(Question q) {
        delete(q);
        add(q);
    }


    private void addMultipleChoice(AllContainer con) {
        try {
            c = DBConncetion.getConnection();
            int id;
            String title;
            int time;
            Questiontype type;
            Stats stats;
            String[] answers;
            String correctAnswers;
            boolean marked;
            stmt = c.createStatement();
            rs = stmt.executeQuery("Select * from mainquestion" +
                    " inner join multiplechoice" +
                    " on mainquestion.id = multiplechoice.id");
            while (rs.next()) {
                answers = new String[4];
                id = rs.getInt("id");
                title = rs.getString("title");
                time = rs.getInt("time");
                marked = rs.getInt("marked") == 1; // 1 in true in Database
                System.out.println(marked);
                type = MultipleChoice;
                stats = new Stats(rs.getInt("answered_correct"),rs.getInt("answered_incorrect"));
                for (int i = 0; i < 4; ++i) answers[i] = rs.getString(9 + i);
                correctAnswers = rs.getString(13);
                con.linkQuestion( new MultipleChoice(title,answers,correctAnswers,time,id,stats,marked));
            }
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() + "1" );
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void addFillBlank(AllContainer con) {
        try {
            c = DBConncetion.getConnection();
            int id;
            String title;
            int time;
            Questiontype type;
            Stats stats;
            String answer;
            boolean marked;
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
                marked = rs.getInt("marked") == 1;
                System.out.println(marked);
                stats = new Stats(rs.getInt("answered_correct"),rs.getInt("answered_incorrect"));
                answer = rs.getString("answer");
                con.linkQuestion(new FillBlank(title,answer,time,id,stats,marked));
            }
    } catch (Exception e) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() + "2" );
        e.printStackTrace();
        System.exit(0);
    }
    }

    public void saveScore(int score) {
        try {
            Connection c = DBConncetion.getConnection();
            String sql = "INSERT INTO Score\n" +
                    "Values (date('now'),?)";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1,String.valueOf(score));
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() + "2" );
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void getScore() {
        try {
            Connection c = DBConncetion.getConnection();
            String sql = "Select *\n" +
                    "From score\n" +
                    "Where rowid = (\n" +
                    "    Select max(rowid) from score\n" +
                    ")";
            stmt = c.createStatement();
            rs = stmt.executeQuery(sql);
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("date"));
            System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(date));
            System.out.println(rs.getInt("score"));

        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() + "2" );
            e.printStackTrace();
            System.exit(0);
        }

    }
}


