package e2e;

import io.restassured.RestAssured;

public class PostApiWithPOJOMultipleUsers {
	
	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://reqres.in";
		// Prepare Request Body
	    UserPOJO userPOJOs = new UserPOJO("morpheus", "leader");

	    UserPOJO responses = RestAssured
	            .given()
	                .header("Content-Type", "application/json")
	                .header("x-api-key", "reqres-free-v1")
	                .body(userPOJOs)
	            .when()
	                .post("/api/users")
	            .then()
	                .statusCode(201) // might be 200 depending on API
	                .extract()
	               .as(UserPOJO.class);

	        System.out.println("Name: " + responses.getName());
	        System.out.println("Job: " + responses.getJob());
	        System.out.println("-------------------------");

		// Validations
//		Assert.assertEquals(name, "Kiran", "Name does not match");
//		Assert.assertEquals(job, "QA Engineer", "Job does not match");
	}
}

