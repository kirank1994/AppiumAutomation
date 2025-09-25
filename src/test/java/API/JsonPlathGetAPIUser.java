package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonPlathGetAPIUser {

    public static void main(String[] args) {
        // Step 1: Send GET request
        Response response = RestAssured
                .given()
                .get("https://jsonplaceholder.typicode.com/users/1");

        // Step 2: Convert response to JsonPath
        JsonPath jsonPath = response.jsonPath();

        // Step 3: Extract main fields
        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String username = jsonPath.getString("username");
        String email = jsonPath.getString("email");
        String phone = jsonPath.getString("phone");
        String website = jsonPath.getString("website");

        // Step 4: Extract nested address
        String street = jsonPath.getString("address.street");
        String suite = jsonPath.getString("address.suite");
        String city = jsonPath.getString("address.city");
        String zipcode = jsonPath.getString("address.zipcode");
        String lat = jsonPath.getString("address.geo.lat");
        String lng = jsonPath.getString("address.geo.lng");

        // Step 5: Extract nested company
        String companyName = jsonPath.getString("company.name");
        String catchPhrase = jsonPath.getString("company.catchPhrase");
        String bs = jsonPath.getString("company.bs");

        // Step 6: Print all values
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Website: " + website);

        System.out.println("\nAddress:");
        System.out.println("  Street: " + street);
        System.out.println("  Suite: " + suite);
        System.out.println("  City: " + city);
        System.out.println("  Zipcode: " + zipcode);
        System.out.println("  Geo: Lat=" + lat + ", Lng=" + lng);

        System.out.println("\nCompany:");
        System.out.println("  Name: " + companyName);
        System.out.println("  CatchPhrase: " + catchPhrase);
        System.out.println("  BS: " + bs);
    }
}