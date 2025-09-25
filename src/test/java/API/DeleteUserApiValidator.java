package API;

import io.restassured.response.Response;

class DeleteUserSchemaValidationException extends Exception {
    public DeleteUserSchemaValidationException(String message) {
        super(message);
    }
}

public class DeleteUserApiValidator {
    public static void main(String[] args) throws Exception {
        validateDeleteUserApiResponse("https://jsonplaceholder.typicode.com/users/1");
    }

    public static void validateDeleteUserApiResponse(String url) throws DeleteUserSchemaValidationException {
        Response response = io.restassured.RestAssured.given()
                .header("Content-Type", "application/json")
                .delete(url);
        // For JSONPlaceholder, DELETE returns 200 and an empty object {}
        if (response.statusCode() != 200) {
            throw new DeleteUserSchemaValidationException("Status code is not 200 (OK)");
        }
        String body = response.getBody().asString();
        System.out.println("API Response:\n" + body);
        // Validate that the response is an empty JSON object
        if (!body.trim().equals("{}")) {
            throw new DeleteUserSchemaValidationException("Response is not an empty JSON object after delete");
        }
        System.out.println("Delete user response validated successfully.");
    }
}
