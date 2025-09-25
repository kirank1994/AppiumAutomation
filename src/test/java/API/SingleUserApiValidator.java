package API;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

class SingleUserSchemaValidationException extends Exception {
    public SingleUserSchemaValidationException(String message) {
        super(message);
    }
}

public class SingleUserApiValidator {
    static {
        // Set base URI for all requests
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://jsonplaceholder.typicode.com")
            .setContentType(ContentType.JSON)
            .build();

    private static final ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();

    public static void main(String[] args) throws Exception {
        User user;
        try {
            user = given()
                .spec(requestSpec)
                .when()
                .get("/users/1")
                .then()
                .spec(responseSpec)
                .extract().as(User.class);
        } catch (Exception e) {
            throw new SingleUserSchemaValidationException("Response is not a valid User object");
        }
        // Print User object as JSON
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        System.out.println("API Response:\n" + json);
        validateUserResponse(user, 1);
        validateNoDuplicateEmails();
    }

    public static void validateUserResponse(User user, int expectedId) throws SingleUserSchemaValidationException {

        // Validate id
        if (user.getId() != expectedId) {
            throw new SingleUserSchemaValidationException("User id does not match expected id: " + expectedId);
        }
        if (user.getId() <= 0) {
            throw new SingleUserSchemaValidationException("User id is not positive");
        }
        // Validate name
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new SingleUserSchemaValidationException("User missing valid 'name'");
        }
        // Validate username
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new SingleUserSchemaValidationException("User missing valid 'username'");
        }
        // Validate email
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new SingleUserSchemaValidationException("User missing valid 'email'");
        }
        if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new SingleUserSchemaValidationException("User has invalid email format: " + user.getEmail());
        }
        // Validate phone
        if (user.getPhone() == null || user.getPhone().trim().isEmpty()) {
            throw new SingleUserSchemaValidationException("User missing valid 'phone'");
        }
        // Validate website
        if (user.getWebsite() == null || user.getWebsite().trim().isEmpty()) {
            throw new SingleUserSchemaValidationException("User missing valid 'website'");
        }
        // Validate address
        Address address = user.getAddress();
        if (address == null) {
            throw new SingleUserSchemaValidationException("User missing 'address'");
        }
        if (address.getStreet() == null || address.getStreet().trim().isEmpty()) {
            throw new SingleUserSchemaValidationException("Address missing valid 'street'");
        }
        if (address.getSuite() == null || address.getSuite().trim().isEmpty()) {
            throw new SingleUserSchemaValidationException("Address missing valid 'suite'");
        }
        if (address.getCity() == null || address.getCity().trim().isEmpty()) {
            throw new SingleUserSchemaValidationException("Address missing valid 'city'");
        }
        if (address.getZipcode() == null || address.getZipcode().trim().isEmpty()) {
            throw new SingleUserSchemaValidationException("Address missing valid 'zipcode'");
        }
        // Validate geo
        Geo geo = address.getGeo();
        if (geo == null) {
            throw new SingleUserSchemaValidationException("Address missing 'geo'");
        }
        if (geo.getLat() == null || geo.getLat().trim().isEmpty()) {
            throw new SingleUserSchemaValidationException("Geo missing valid 'lat'");
        }
        if (geo.getLng() == null || geo.getLng().trim().isEmpty()) {
            throw new SingleUserSchemaValidationException("Geo missing valid 'lng'");
        }
        // Validate company
        Company company = user.getCompany();
        if (company == null) {
            throw new SingleUserSchemaValidationException("User missing 'company'");
        }
        if (company.getName() == null || company.getName().trim().isEmpty()) {
            throw new SingleUserSchemaValidationException("Company missing valid 'name'");
        }
        if (company.getCatchPhrase() == null || company.getCatchPhrase().trim().isEmpty()) {
            throw new SingleUserSchemaValidationException("Company missing valid 'catchPhrase'");
        }
        if (company.getBs() == null || company.getBs().trim().isEmpty()) {
            throw new SingleUserSchemaValidationException("Company missing valid 'bs'");
        }
        System.out.println("Single user response schema validated successfully.");
    }

    public static void validateNoDuplicateEmails() throws SingleUserSchemaValidationException {
        User[] users = given()
            .spec(requestSpec)
            .when()
            .get("/users")
            .then()
            .spec(responseSpec)
            .extract().as(User[].class);
        java.util.Set<String> emailSet = new java.util.HashSet<>();
        for (User u : users) {
            if (!emailSet.add(u.getEmail())) {
                throw new SingleUserSchemaValidationException("Duplicate email found: " + u.getEmail());
            }
        }
        System.out.println("No duplicate emails found in all users.");
    }
}