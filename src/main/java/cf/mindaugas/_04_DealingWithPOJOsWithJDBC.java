package cf.mindaugas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
class Customer {
    private Long id;
    private String name;
}

class CustomerDAO {
    private static final String connStr = "jdbc:mysql://localhost:3306/joinsexample?profileSQL=true";

    public static List<Customer> getAll(){
        var customers = new ArrayList<Customer>();
        try(
            var conn = DriverManager.getConnection(connStr, "root", "root");
            var stmt = conn.prepareStatement("SELECT * FROM customer");
        ) {
            var rs = stmt.executeQuery();
            while(rs.next()) customers.add(new Customer(rs.getLong(1), rs.getString(2)));
        } catch (SQLException e){
            System.out.println("[ERROR] General database error: " + e);
        }
        return customers;
    }
    public static ResultSet get(Long id){
        return null;
    }

    public static void save(Customer customer) {
        try(
            var conn = DriverManager.getConnection(connStr, "root", "root");
            var stmt = conn.prepareStatement("INSERT INTO customer VALUES (?, ?)");
        ) {
            stmt.setLong(1, customer.getId());
            stmt.setString(2, customer.getName());
            var res = stmt.executeUpdate();
            System.out.println("Records created: " + res);
        } catch (SQLException e){
            System.out.println("[ERROR] General database error: " + e);
        }
    }

    public static void saveAll(Collection<Customer> customers){
        // Homework exercise
        // ... for all customer inserts only single connection and single statement should be executed
        // ... measure the performance difference when 100, 1_000, 10_000 and maybe more are inserted
    }
}

public class _04_DealingWithPOJOsWithJDBC {
    public static void main(String[] args) {
        CustomerDAO.save(new Customer(50L, "John"));
        CustomerDAO.save(new Customer(51L, "Max"));
        System.out.println(CustomerDAO.getAll());
    }
}
