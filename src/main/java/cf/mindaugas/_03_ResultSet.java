package cf.mindaugas;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class _03_ResultSet {
    public static void main(String[] args) {
        var connStr = "jdbc:mysql://localhost:3306/joinsexample?profileSQL=true";
        try(
            var conn = DriverManager.getConnection(connStr, "root", "root");
            var stmt = conn.prepareStatement("SELECT * FROM orders", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ){
            var rslt = stmt.executeQuery();

            while (rslt.next())
                System.out.println(
                    rslt.getString(1) + " " +
                    rslt.getString(2) + " " +
                    rslt.getString(3)
                );

            // jump back to the start of the result set ... so you can iterate again
            // ... ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY
            rslt.beforeFirst();

            while (rslt.next())
                System.out.println(
                    rslt.getString(1) + " " +
                    rslt.getString(2) + " " +
                    rslt.getString(3)
                );
        } catch (SQLException e){
            System.out.println("[ERROR] General database error: " + e);
        }
    }
}
