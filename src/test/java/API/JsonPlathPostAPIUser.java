package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

public class JsonPlathPostAPIUser {

    public static void main(String[] args) {
        // Step 1: Prepare JSON body as String
        String requestBody = """
        {
          "id": 1,
          "name": "Leanne Graham",
          "username": "Bret",
          "email": "Sincere@april.biz",
          "address": {
            "street": "Kulas Light",
            "suite": "Apt. 556",
            "city": "Gwenborough",
            "zipcode": "92998-3874",
            "geo": {
              "lat": "-37.3159",
              "lng": "81.1496"
            }
          },
          "phone": "1-770-736-8031 x56442",
          "website": "hildegard.org",
          "company": {
            "name": "Romaguera-Crona",
            "catchPhrase": "Multi-layered client-server neural-net",
            "bs": "harness real-time e-markets"
          }
        }
        """;

        // Step 2: Send POST request
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("https://jsonplaceholder.typicode.com/users");

        // Step 3: Convert response to JsonPath
        JsonPath jsonPath = response.jsonPath();

        // Step 4: Extract main fields
        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String username = jsonPath.getString("username");
        String email = jsonPath.getString("email");
        String phone = jsonPath.getString("phone");
        String website = jsonPath.getString("website");

        // Step 5: Extract nested address
        String street = jsonPath.getString("address.street");
        String suite = jsonPath.getString("address.suite");
        String city = jsonPath.getString("address.city");
        String zipcode = jsonPath.getString("address.zipcode");
        String lat = jsonPath.getString("address.geo.lat");
        String lng = jsonPath.getString("address.geo.lng");

        // Step 6: Extract nested company
        String companyName = jsonPath.getString("company.name");
        String catchPhrase = jsonPath.getString("company.catchPhrase");
        String bs = jsonPath.getString("company.bs");

        // Step 7: Print all values
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
