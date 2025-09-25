package API;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnect {

    public static void main(String[] args) throws SQLException {
        //Step 1: establish a connection to the given database URL
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb", "root", "");
        //Step 2: create a statement object to perform a query
        Statement stmt = con.createStatement();
        // STEP 3: Execute a query to retrieve data from the database
        ResultSet rs = stmt.executeQuery("select * from users where id=1");
        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
        }
        // CREATE operation
        int insertCount = stmt.executeUpdate("INSERT INTO users (name, email) VALUES ('NewUser', 'newuser@example.com')");
        System.out.println("Inserted rows: " + insertCount);
        // UPDATE operation
        int updateCount = stmt.executeUpdate("UPDATE users SET name='UpdatedUser' WHERE id=1");
        System.out.println("Updated rows: " + updateCount);
        // DELETE operation
        int deleteCount = stmt.executeUpdate("DELETE FROM users WHERE id=2");
        System.out.println("Deleted rows: " + deleteCount);
       
//        // CREATE TABLE operation for employee
//        String createEmployeeTableSQL = "CREATE TABLE IF NOT EXISTS employee (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), email VARCHAR(255), department VARCHAR(255))";
//        stmt.executeUpdate(createEmployeeTableSQL);
      
        con.close();
    }
}