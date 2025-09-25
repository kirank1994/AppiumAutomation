package API;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

class CreateUserSchemaValidationException extends Exception {
    public CreateUserSchemaValidationException(String message) {
        super(message);
    }
}

public class CreateUserApiValidator {
    static {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    public static void main(String[] args) throws Exception {
        User userPayload = new User();
        userPayload.setName("John Doe");
        userPayload.setUsername("johndoe");
        userPayload.setEmail("johndoe@example.com");
        userPayload.setPhone("1234567890");
        userPayload.setWebsite("example.com");
        Address address = new Address();
        address.setStreet("123 Main St");
        address.setSuite("Apt 101");
        address.setCity("Metropolis");
        address.setZipcode("12345");
        Geo geo = new Geo();
        geo.setLat("12.3456");
        geo.setLng("78.9012");
        address.setGeo(geo);
        userPayload.setAddress(address);
        Company company = new Company();
        company.setName("Acme Corp");
        company.setCatchPhrase("Innovation for Tomorrow");
        company.setBs("synergize scalable solutions");
        userPayload.setCompany(company);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // Omit null fields
        //String jsonPayload = mapper.writeValueAsString(userPayload);
        //System.out.println("Request Payload:\n" + jsonPayload);
        Response response = given()
                .header("Content-Type", "application/json")
                .body(userPayload)
                .when()
                .post("/users")
                .then()
                //.statusCode(239)//Exception in thread "main" java.lang.AssertionError: 1 expectation failed.Expected status code <239> but was <201>.
                .extract().response();
       Assert.assertEquals(response.getStatusCode(), 201, "Status code mismatch"); //Exception in thread "main" java.lang.AssertionError: Status code mismatch expected [211] but found [201]

        System.out.println("API Response:\n" + response.getBody().asString());
        validateCreateUserApiResponse(response);
    }

    public static void validateCreateUserApiResponse(Response response) throws CreateUserSchemaValidationException {
        if (response.statusCode() != 201) { // 201 Created is expected for POST
            throw new CreateUserSchemaValidationException("Status code is not 201 (Created)"); 
          //Exception in thread "main" API.CreateUserSchemaValidationException: Status code is not 201 (Created)
        }
        String body = response.getBody().asString();
        ObjectMapper mapper = new ObjectMapper();
        User user;
        try {
            // Validates that the response is a single object (User)
            user = mapper.readValue(body, User.class);
        } catch (Exception e) {
            throw new CreateUserSchemaValidationException("Response is not a valid User object");
        }
        // Validates that the object has id (number and positive)
        if (user.getId() <= 0) {
            throw new CreateUserSchemaValidationException("User id is not positive");
        }
        // Validates that the object has name (string, not just whitespace)
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new CreateUserSchemaValidationException("User missing valid 'name'");
        }
        // Validates that the object has email (string, not empty, valid format)
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new CreateUserSchemaValidationException("User missing valid 'email'");
        }
        if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new CreateUserSchemaValidationException("User has invalid email format: " + user.getEmail());
        }
        System.out.println("Create user response schema validated successfully.");
    }
}