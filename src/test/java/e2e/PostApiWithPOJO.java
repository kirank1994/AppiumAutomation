package e2e;

import io.restassured.RestAssured;

public class PostApiWithPOJO {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://reqres.in";
        // Prepare Request Body
        UserPOJO userPOJOs = 
            new UserPOJO("morpheus", "leader");

        // Send each user as a separate POST request and collect responses

            PostUserResponse responseUser = RestAssured
                .given()
                    .header("Content-Type", "application/json")
                    .header("x-api-key", "reqres-free-v1")
                    .body(userPOJOs)
                .when()
                    .post("/api/users")
                .then()
                    .statusCode(201)
                    .extract()
                    .as(PostUserResponse.class);

            System.out.println("Id: " + responseUser.getId());
            System.out.println("Name: " + responseUser.getName());
            System.out.println("Job: " + responseUser.getJob());
            System.out.println("CreatedAt: " + responseUser.getCreatedAt());
            System.out.println("-------------------------");
        

        // Validations
//		Assert.assertEquals(name, "Kiran", "Name does not match");
//		Assert.assertEquals(job, "QA Engineer", "Job does not match");
    }
}