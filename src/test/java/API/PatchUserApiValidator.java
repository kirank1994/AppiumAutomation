package API;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

class PatchUserSchemaValidationException extends Exception {
    public PatchUserSchemaValidationException(String message) {
        super(message);
    }
}

public class PatchUserApiValidator {
    public static void main(String[] args) throws Exception {
        String jsonPayload = "{\n  \"email\": \"updatedemail@example.com\"\n}";
        validatePatchUserApiResponse("https://jsonplaceholder.typicode.com/users/1", jsonPayload, "updatedemail@example.com");
    }

    public static void validatePatchUserApiResponse(String url, String jsonPayload, String expectedEmail) throws PatchUserSchemaValidationException {
        Response response = io.restassured.RestAssured.given()
                .header("Content-Type", "application/json")
                .body(jsonPayload)
                .patch(url);
        if (response.statusCode() != 200) { // 200 OK is expected for PATCH
            throw new PatchUserSchemaValidationException("Status code is not 200 (OK)");
        }
        String body = response.getBody().asString();
        System.out.println("API Response:\n" + body);
        ObjectMapper mapper = new ObjectMapper();
        User user;
        try {
            // Validates that the response is a single object (User)
            user = mapper.readValue(body, User.class);
        } catch (Exception e) {
            throw new PatchUserSchemaValidationException("Response is not a valid User object");
        }
        // Validates that the object has id (number and positive)
        if (user.getId() <= 0) {
            throw new PatchUserSchemaValidationException("User id is not positive");
        }
        // Validates that the object has email (string, not empty, valid format)
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new PatchUserSchemaValidationException("User missing valid 'email'");
        }
        if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new PatchUserSchemaValidationException("User has invalid email format: " + user.getEmail());
        }
        // Validates that the email matches the updated value
        if (!user.getEmail().equals(expectedEmail)) {
            throw new PatchUserSchemaValidationException("User email does not match expected updated email");
        }
        System.out.println("Patch user response schema validated successfully.");
    }
}
