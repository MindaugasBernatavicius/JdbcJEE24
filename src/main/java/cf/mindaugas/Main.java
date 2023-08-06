package cf.mindaugas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static Connection getConnection() throws SQLException {
        var connStr = "jdbc:mysql://localhost:3306/joinsexample";
        return DriverManager.getConnection(connStr, "root", "root");
    }

    public static void main(String[] args) throws SQLException {
        var conn = getConnection();
        var stmt = conn.prepareStatement("SELECT * FROM orders");
        var rslt = stmt.executeQuery();
        while(rslt.next())
            System.out.println(
                    rslt.getString(1) + " " +
                    rslt.getString(2) + " " +
                    rslt.getString(3)
            );
    }
}