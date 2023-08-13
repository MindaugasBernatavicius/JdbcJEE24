package cf.mindaugas;

import java.sql.DriverManager;
import java.sql.SQLException;

public class _01_Statements {
    public static void _00_PreparedStatements(){
        var connStr = "jdbc:mysql://localhost:3306/joinsexample?profileSQL=true";

        var userControlledInput = "\"1\""; // SELECT * FROM orders WHERE id = "1"
        // var userControlledInput = "1"; // SELECT * FROM orders WHERE id = 1
        // var userControlledInput = "1--; SELECT 1;"; // DOES NOT WORK
        // var userControlledInput = "'1'--' AND password = 'password'"; // SELECT * FROM orders WHERE id = "1"
        try(
                // var stmt = conn.prepareStatement("SELECT * FROM orders");
                // var rslt = stmt.executeQuery();
                // var stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                // var rslt = stmt.executeQuery("SELECT * FROM orders WHERE id = " + userControlledInput);

                var conn = DriverManager.getConnection(connStr, "root", "root");
                var stmt = conn.prepareStatement("SELECT * FROM order WHERE id = ?");
        ){
            stmt.setLong(1, 1L);
            var rslt = stmt.executeQuery();

            while (rslt.next())
                System.out.println(
                    rslt.getString(1) + " " +
                    rslt.getString(2) + " " +
                    rslt.getString(3)
                );

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

    public static void main(String[] args) {
        _00_PreparedStatements();
    }
}
