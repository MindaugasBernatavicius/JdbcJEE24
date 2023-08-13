package cf.mindaugas;

import java.sql.DriverManager;
import java.sql.SQLException;

public class _02_Updates {
    public static void main(String[] args) {
        var connStr = "jdbc:mysql://localhost:3306/joinsexample?profileSQL=true";
        try(
            var conn = DriverManager.getConnection(connStr, "root", "root");
            var stmt = conn.prepareStatement("INSERT INTO orders (`Item`, `Customer_id`) VALUES (?, ?), (?, ?)");
        ){
            stmt.setString(1, "Desk");
            stmt.setLong(2, 3L);

            // ... we can even insert multiple values into the database
            stmt.setString(3, "Chair");
            stmt.setLong(4, 3L);

            var rslt = stmt.executeUpdate();
            System.out.println(rslt); // affected row count

        } catch (SQLException e){
            System.out.println("[ERROR] General database error: " + e);
        }
    }
}
