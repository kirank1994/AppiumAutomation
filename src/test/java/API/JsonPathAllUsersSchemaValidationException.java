package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.List;
import java.util.Map;
import java.util.HashSet;

public class JsonPathAllUsersSchemaValidationException {
    static {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    public static void main(String[] args) throws Exception {
        // Step 1: Send GET request
        Response response = RestAssured
                .given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Step 2: Convert response to JsonPath
        JsonPath jsonPath = response.jsonPath();

        // Step 3: Get list of all users as List of Maps
        List<Map<String, Object>> users = jsonPath.getList("$");

        // Step 4: Print all users
        for (int i = 0; i < users.size(); i++) {
            Map<String, Object> user = users.get(i);
            System.out.println("User " + (i + 1) + ":");
            System.out.println("  ID: " + user.get("id"));
            System.out.println("  Name: " + user.get("name"));
            System.out.println("  Username: " + user.get("username"));
            System.out.println("  Email: " + user.get("email"));
            System.out.println("  Phone: " + user.get("phone"));
            System.out.println("  Website: " + user.get("website"));

            Map<String, Object> address = (Map<String, Object>) user.get("address");
            System.out.println("  Address:");
            System.out.println("    Street: " + address.get("street"));
            System.out.println("    Suite: " + address.get("suite"));
            System.out.println("    City: " + address.get("city"));
            System.out.println("    Zipcode: " + address.get("zipcode"));

            Map<String, Object> geo = (Map<String, Object>) address.get("geo");
            System.out.println("    Geo: Lat=" + geo.get("lat") + ", Lng=" + geo.get("lng"));

            Map<String, Object> company = (Map<String, Object>) user.get("company");
            System.out.println("  Company:");
            System.out.println("    Name: " + company.get("name"));
            System.out.println("    CatchPhrase: " + company.get("catchPhrase"));
            System.out.println("    BS: " + company.get("bs"));

            System.out.println("--------------------------------------------------");
        }

        // Step 5: Optional: Validate users
        validateUsers(users);
        System.out.println("All users response schema validated successfully using JsonPath.");
    }

    public static void validateUsers(List<Map<String, Object>> users) throws AllUsersSchemaValidationException {
        if (users == null || users.isEmpty()) {
            throw new AllUsersSchemaValidationException("User list is empty or null");
        }

        HashSet<String> emailSet = new HashSet<>();

        for (int i = 0; i < users.size(); i++) {
            Map<String, Object> user = users.get(i);

            Integer id = (Integer) user.get("id");
            String name = (String) user.get("name");
            String username = (String) user.get("username");
            String email = (String) user.get("email");
            String phone = (String) user.get("phone");
            String website = (String) user.get("website");
            Map<String, Object> address = (Map<String, Object>) user.get("address");
            Map<String, Object> geo = (Map<String, Object>) address.get("geo");
            Map<String, Object> company = (Map<String, Object>) user.get("company");

            if (id == null || id <= 0) throw new AllUsersSchemaValidationException("User " + i + " invalid ID");
            if (name == null || name.trim().isEmpty()) throw new AllUsersSchemaValidationException("User " + i + " missing name");
            if (username == null || username.trim().isEmpty()) throw new AllUsersSchemaValidationException("User " + i + " missing username");
            if (email == null || email.trim().isEmpty()) throw new AllUsersSchemaValidationException("User " + i + " missing email");
            if (!emailSet.add(email)) throw new AllUsersSchemaValidationException("Duplicate email found: " + email);
            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) throw new AllUsersSchemaValidationException("Invalid email format: " + email);
            if (phone == null || phone.trim().isEmpty()) throw new AllUsersSchemaValidationException("User " + i + " missing phone");
            if (website == null || website.trim().isEmpty()) throw new AllUsersSchemaValidationException("User " + i + " missing website");

            if (address == null) throw new AllUsersSchemaValidationException("User " + i + " missing address");
            if (address.get("street") == null || ((String) address.get("street")).trim().isEmpty()) throw new AllUsersSchemaValidationException("Address missing street for user " + i);
            if (address.get("suite") == null || ((String) address.get("suite")).trim().isEmpty()) throw new AllUsersSchemaValidationException("Address missing suite for user " + i);
            if (address.get("city") == null || ((String) address.get("city")).trim().isEmpty()) throw new AllUsersSchemaValidationException("Address missing city for user " + i);
            if (address.get("zipcode") == null || ((String) address.get("zipcode")).trim().isEmpty()) throw new AllUsersSchemaValidationException("Address missing zipcode for user " + i);

            if (geo == null) throw new AllUsersSchemaValidationException("User " + i + " missing geo");
            if (geo.get("lat") == null || ((String) geo.get("lat")).trim().isEmpty()) throw new AllUsersSchemaValidationException("Geo missing lat for user " + i);
            if (geo.get("lng") == null || ((String) geo.get("lng")).trim().isEmpty()) throw new AllUsersSchemaValidationException("Geo missing lng for user " + i);

            if (company == null) throw new AllUsersSchemaValidationException("User " + i + " missing company");
            if (company.get("name") == null || ((String) company.get("name")).trim().isEmpty()) throw new AllUsersSchemaValidationException("Company missing name for user " + i);
            if (company.get("catchPhrase") == null || ((String) company.get("catchPhrase")).trim().isEmpty()) throw new AllUsersSchemaValidationException("Company missing catchPhrase for user " + i);
            if (company.get("bs") == null || ((String) company.get("bs")).trim().isEmpty()) throw new AllUsersSchemaValidationException("Company missing bs for user " + i);
        }
    }
}