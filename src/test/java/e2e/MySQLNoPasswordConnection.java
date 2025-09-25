package e2e;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.Assert;

public class MySQLNoPasswordConnection {

	public static void main(String[] args) {
		String jdbcURL = "jdbc:mysql://127.0.0.1:3306/mydb";
		String username = "root";
		String password = "";

		Connection connection = null;

		try {
			// Step 1: Connect
			connection = DriverManager.getConnection(jdbcURL, username, password);
			System.out.println("✅ Connected to MySQL database (no password)");

			// Step 2: Execute a query
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users");

			// Step 3: Fetch data from UI (example)
                                              //String expectedIDText = driver.findElement(By.id("com.androidsample.generalstore:id/productID")).getText();
               //int expectedID = Integer.parseInt(expectedIDText.trim());
			int expectedID = 1; 
			String expectedName = "Jane Doe";
			String expectedEmail = "john@example.com";

			// Step 4: Iterate through result set
			while (rs.next()) {
				int actualID = rs.getInt("id");
				String actualName = rs.getString("name");
				String actualEmail = rs.getString("email");

				// Optional: print data
				System.out.println("ID: " + actualID + ", Name: " + actualName + ", Email: " + actualEmail);

				// Example: validate only first row
				Assert.assertEquals(actualID, expectedID, "ID mismatch");
				Assert.assertEquals(actualName, expectedName, "Name mismatch");
				Assert.assertEquals(actualEmail, expectedEmail, "Email mismatch");

				break; // validate only the first row
			}

		} catch (Exception e) {
			System.err.println("❌ Error: " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
				System.out.println("🔌 Connection closed.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
