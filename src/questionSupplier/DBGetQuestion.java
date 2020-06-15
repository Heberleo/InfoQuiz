package questionSupplier;

import questions.FillBlank;
import questions.MultipleChoice;
import questions.Question;

import javax.print.DocFlavor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class DBGetQuestion {
    public static Question getRandomQuestion() {
        Random randy;
        Connection c;
        Statement stmt;
        ResultSet rs = null;
        try {
            randy = new Random();
            c = DBConncetion.getConnection();
            stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT max(id) from mainquestion");
            rs.next();
            int randomId = randy.nextInt(rs.getInt(1) + 1);
            rs = stmt.executeQuery("SELECT * From mainquestion Where id = " + randomId);
            return createQuestion(rs);
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return null;
    }

    public static Question createQuestion(ResultSet rs) {
        Question question;
        int id;
        int time;
        String title;
        int type;
        int answeredCorrect;
        int answeredIncorrect;
        try {
            rs.next();
            id = rs.getInt(1);
            title = rs.getString(2);
            time = rs.getInt(3);
            type = rs.getInt(4);
            answeredCorrect = rs.getInt(5);
            answeredIncorrect = rs.getInt(6);
            if (type == 1) {
                return question = new MultipleChoice(title,getAnswerMultibleChoice(id),getCorrectAnswerMultipleChoice(1),time);
            }
            if (type == 2) {
                return  question = new FillBlank(title,getAnswerFillBLank(id),time);
            }

        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return null;
    }

    public static String[] getAnswerMultibleChoice(int id) {
        String[] answers = null;
        Connection c;
        Statement stmt;
        ResultSet rs = null;
        try {
            answers = new String[4];
            c = DBConncetion.getConnection();
            stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT* FROM multiplechoice Where id =" + id);
            rs.next();
            answers[0] = rs.getString(2);
            answers[1] = rs.getString(3);
            answers[2] = rs.getString(4);
            answers[3] = rs.getString(5);
            return answers;
        } catch (Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return answers;
    }

    public static int[] getCorrectAnswerMultipleChoice(int id) {
        int temp;
        int[] answers = null;
        Connection c;
        Statement stmt;
        ResultSet rs = null;
        try {
            answers = new int[4];
            c = DBConncetion.getConnection();
            stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT correct_answer FROM multiplechoice Where id =" + id);
            rs.next();
            temp = rs.getInt("correct_answer");
            answers[0] = temp; //needs to be uptdated !!!
            return answers;
        } catch (Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return answers;
    }







    /**
     * returns the answer for the FillBlank question with the specified id
     * @param id
     * @return the answer
     */
    public static String getAnswerFillBLank(int id) {
        String answer = null;
        Connection c;
        Statement stmt;
        ResultSet rs = null;
        try {
            c = DBConncetion.getConnection();
            stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT* FROM fill_blank Where id =" + id);
            rs.next();
            answer = rs.getString(2);
            return answer;

        } catch (Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return answer;
    }

}
