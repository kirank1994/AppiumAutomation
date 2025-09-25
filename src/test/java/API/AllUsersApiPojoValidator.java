package API;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashSet;

class AllUsersSchemaValidationException extends Exception {
    private static final long serialVersionUID = 1L;

	public AllUsersSchemaValidationException(String message) {
        super(message);
    }
}

public class AllUsersApiPojoValidator {
    static {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    public static void main(String[] args) throws Exception {
        User[] users = given()
            .when()
            .get("/users")
            .then()
            .statusCode(200)
            .extract().as(User[].class);
//for(int i=0;i<users.length;i++){
//		System.out.println(users[i].getAddress().getCity());
//}
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(users);
        System.out.println("API Response:\n" + json);
        validateUsers(users);
        System.out.println("All users response schema validated successfully.");
    }

    public static void validateUsers(User[] users) throws AllUsersSchemaValidationException {
        if (users == null || users.length == 0) {
            throw new AllUsersSchemaValidationException("User array is empty or null");
        }
        HashSet<String> emailSet = new HashSet<>();
        for (int i = 0; i < users.length; i++) {
            User user = users[i];
            if (user.getId() <= 0) {
                throw new AllUsersSchemaValidationException("User at index " + i + " missing valid 'id'");
            }
            if (user.getName() == null || user.getName().trim().isEmpty()) {
                throw new AllUsersSchemaValidationException("User at index " + i + " missing valid 'name'");
            }
            if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
                throw new AllUsersSchemaValidationException("User at index " + i + " missing valid 'username'");
            }
            if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
                throw new AllUsersSchemaValidationException("User at index " + i + " missing valid 'email'");
            }
            if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                throw new AllUsersSchemaValidationException("User at index " + i + " has invalid email format: " + user.getEmail());
            }
            if (!emailSet.add(user.getEmail())) {
                throw new AllUsersSchemaValidationException("Duplicate email found: " + user.getEmail());
            }
            if (user.getPhone() == null || user.getPhone().trim().isEmpty()) {
                throw new AllUsersSchemaValidationException("User at index " + i + " missing valid 'phone'");
            }
            if (user.getWebsite() == null || user.getWebsite().trim().isEmpty()) {
                throw new AllUsersSchemaValidationException("User at index " + i + " missing valid 'website'");
            }
            Address address = user.getAddress();
            if (address == null) {
                throw new AllUsersSchemaValidationException("User at index " + i + " missing 'address'");
            }
            if (address.getStreet() == null || address.getStreet().trim().isEmpty()) {
                throw new AllUsersSchemaValidationException("Address missing valid 'street' for user at index " + i);
            }
            if (address.getSuite() == null || address.getSuite().trim().isEmpty()) {
                throw new AllUsersSchemaValidationException("Address missing valid 'suite' for user at index " + i);
            }
            if (address.getCity() == null || address.getCity().trim().isEmpty()) {
                throw new AllUsersSchemaValidationException("Address missing valid 'city' for user at index " + i);
            }
            if (address.getZipcode() == null || address.getZipcode().trim().isEmpty()) {
                throw new AllUsersSchemaValidationException("Address missing valid 'zipcode' for user at index " + i);
            }
            Geo geo = address.getGeo();
            if (geo == null) {
                throw new AllUsersSchemaValidationException("Address missing 'geo' for user at index " + i);
            }
            if (geo.getLat() == null || geo.getLat().trim().isEmpty()) {
                throw new AllUsersSchemaValidationException("Geo missing valid 'lat' for user at index " + i);
            }
            if (geo.getLng() == null || geo.getLng().trim().isEmpty()) {
                throw new AllUsersSchemaValidationException("Geo missing valid 'lng' for user at index " + i);
            }
            Company company = user.getCompany();
            if (company == null) {
                throw new AllUsersSchemaValidationException("User at index " + i + " missing 'company'");
            }
            if (company.getName() == null || company.getName().trim().isEmpty()) {
                throw new AllUsersSchemaValidationException("Company missing valid 'name' for user at index " + i);
            }
            if (company.getCatchPhrase() == null || company.getCatchPhrase().trim().isEmpty()) {
                throw new AllUsersSchemaValidationException("Company missing valid 'catchPhrase' for user at index " + i);
            }
            if (company.getBs() == null || company.getBs().trim().isEmpty()) {
                throw new AllUsersSchemaValidationException("Company missing valid 'bs' for user at index " + i);
            }
        }
    }
}
