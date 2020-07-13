package Database;

import java.sql.*;

public class DBConncetion {
    static Connection c;
    public static void connect() throws LoadSaveException {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:questiondb.db");

        } catch ( Exception e ) {
            throw new LoadSaveException("Error while Connecting: " + e.getMessage() );
        }
    }
    public static Connection getConnection() {return c;}
    public static void  closeConnection() throws LoadSaveException{
        try {
            c.close();
        } catch (Exception e) {
            throw new LoadSaveException("Error while Disconnecting:" + e.getMessage());
        }
    }
}
