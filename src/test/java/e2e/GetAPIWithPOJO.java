package e2e;

import io.restassured.RestAssured;

public class GetAPIWithPOJO {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://reqres.in";
        UserPOJO userPOJO = RestAssured
            .given()
                .header("x-api-key", "reqres-free-v1")
                .queryParam("id", "12")
            .when()
                .get("/api/users")
            .then()
                .statusCode(200)
                .extract().as(UserPOJO.class);

       // User user = response.jsonPath().getObject("data", User.class);
        System.out.println("Id: " + userPOJO.getId());
        System.out.println("Email: " + userPOJO.getEmail());
        System.out.println("First Name: " + userPOJO.getFirst_name());
        System.out.println("Last Name: " + userPOJO.getLast_name());
        System.out.println("Avatar: " + userPOJO.getAvatar());
    }
}