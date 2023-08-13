package cf.mindaugas;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import java.sql.*;

class DbManager implements AutoCloseable {
    private final Connection conn;
    private PreparedStatement stmt;
    public DbManager(String user, String pass, Boolean debugSQL) throws SQLException {
        var connStr = debugSQL
                ? "jdbc:mysql://localhost:3306/joinsexample?profileSQL=true"
                : "jdbc:mysql://localhost:3306/joinsexample";
        conn = DriverManager.getConnection(connStr, user, pass);
    }

    public ResultSet executeSelect(String selectQ) throws SQLException {
        stmt = conn.prepareStatement(selectQ);
        return stmt.executeQuery();
    }

    public void close() throws SQLException {
        stmt.close();
        conn.close();
    }
}

public class _00_JDBCConnectionManagement {
    static void _00_PreJava7WayToHandleConnections(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rslt = null;
        try {
            var connStr = "jdbc:mysql://localhost:3306/joinsexample?profileSQL=true"; //
            conn = DriverManager.getConnection(connStr, "root", "root");
            stmt = conn.prepareStatement("SELECT * FROM ordersx");
            rslt = stmt.executeQuery();
            while (rslt.next())
                System.out.println(
                    rslt.getString(1) + " " +
                    rslt.getString(2) + " " +
                    rslt.getString(3)
                );

        } catch (CommunicationsException e){
            System.out.println("[ERROR] Error with db connection: " + e);
        } catch (SQLException e){
            // ... to provoke this, just try querying non-existent table (SQLSyntaxErrorException).
            // ... or just enter an invalid statement (SQLSyntaxErrorException)
            System.out.println("[ERROR] General database error: " + e);
        } finally {
            try {
                if(rslt != null) rslt.close();
            } catch (SQLException e) {
                System.out.println("[ERROR] Error when cleaning up: " + e);
            }
            try {
                if(stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("[ERROR] Error when cleaning up: " + e);
            }
            try {
                if(conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("[ERROR] Error when cleaning up: " + e);
            }
        }

        System.out.println("... application continues");
    }

    static void _01_AutocloseableForJDBCObjects(){
        var connStr = "jdbc:mysql://localhost:3306/joinsexample?profileSQL=true";
        try(var conn = DriverManager.getConnection(connStr, "root", "root");
            var stmt = conn.prepareStatement("SELECT * FROM orders");
            var rslt = stmt.executeQuery()){

            while (rslt.next())
                System.out.println(
                    rslt.getString(1) + " " +
                    rslt.getString(2) + " " +
                    rslt.getString(3)
                );

        } catch (CommunicationsException e){
            System.out.println("[ERROR] Error with db connection: " + e);
        } catch (SQLException e){
            // ... to provoke this, just try querying non-existent table (SQLSyntaxErrorException).
            // ... or just enter an invalid statement (SQLSyntaxErrorException)
            System.out.println("[ERROR] General database error: " + e);
        }

        System.out.println("... application continues");
    }

    static void _02_UsingDatabaseManager(){
        try (var dbManager = new DbManager("root", "root", false)) {
            var result = dbManager.executeSelect("SELECT * FROM orders");
            while (result.next())
                System.out.println(
                    result.getString(1) + " " +
                    result.getString(2) + " " +
                    result.getString(3)
                );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        // _00_PreJava7WayToHandleConnections();
        // _01_AutocloseableForJDBCObjects();
        _02_UsingDatabaseManager(); // ... and you can create more and better abstractions ...
    }
}