package LLL;
import java.sql.*;
public class bd {

    public static void main(String[] args) {




        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            //  String sql = "CREATE TABLE AUTO " +
            //         " (ID INT PRIMARY KEY     NOT NULL," +
            //           " Login           TEXT    NOT NULL, " +
            //          " Pas            TEXT     NOT NULL ) ";
            //  stmt.executeUpdate(sql);
            String sq2 =  "INSERT INTO AUTO (ID,Login,Pas) " +
                    "VALUES (4, 'Login4', 'Pass4');";
            stmt.executeUpdate(sq2);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");

    }
}
