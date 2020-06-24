package Database;

import Management.FillBlank;
import Management.MultipleChoice;
import Management.Question;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class DBGetQuestion {
    public Question getRandomQuestion() {
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
            int randomId = randy.nextInt(rs.getInt(1)) + 1;
            rs.close();
            return createQuestion(randomId);
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() +"1" );
            System.exit(0);
        }
        return null;
    }

    public Question createQuestion(int id) {
        ResultSet rs = null;
        Connection c;
        Statement stmt;
        Question question;
        int time = 0;
        String title = null;
        int type = 0;
        int answeredCorrect;
        int answeredIncorrect;
        try {
            c = DBConncetion.getConnection();
            stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT * From mainquestion Where id = " + id);
            rs.next();
            id = rs.getInt(1);
            title = rs.getString(2);
            time = rs.getInt(3);
            type = rs.getInt(4);
            answeredCorrect = rs.getInt(5);
            answeredIncorrect = rs.getInt(6);
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() +"2" );
            e.printStackTrace();
            System.exit(0);
        }
        if (type == 1) {
            return question = new MultipleChoice(title,getAnswerMultibleChoice(id),getCorrectAnswerMultipleChoice(1),time, id);
        }
        if (type == 2) {
            return  question = new FillBlank(title,getAnswerFillBLank(id),time, id);
        }
        return null;
    }

    public String[] getAnswerMultibleChoice(int id) {
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
            rs.close();
            return answers;
        } catch (Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage()+ "3" );
            System.exit(0);
        }
        return answers;
    }

    public int[] getCorrectAnswerMultipleChoice(int id) {
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
            rs.close();
            return answers;
        } catch (Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() + "4" );
            System.exit(0);
        }
        return answers;
    }







    /**
     * returns the answer for the FillBlank question with the specified id
     * @param id
     * @return the answer
     */
    public String getAnswerFillBLank(int id) {
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
            System.err.println( e.getClass().getName() + ": " + e.getMessage() +"5" );
            System.exit(0);
        }
        return answer;
    }

}
