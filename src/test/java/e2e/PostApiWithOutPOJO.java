package e2e;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

//import org.json.JSONObject;
import org.testng.Assert;

public class PostApiWithOutPOJO {
	
	public static void main(String[] args) {
		
//		RestAssured.baseURI = "https://reqres.in";
//		// Prepare Request Body
//			//	JSONObject requestBody = new JSONObject();
//				requestBody.put("name", "Kiran");
//				requestBody.put("job", "QA Engineer");
//
//
//		String response = RestAssured
//				.given()
//					.header("x-api-key", "reqres-free-v1")
//					.header("Content-Type","application/json")
//				    .body(requestBody.toString()) //Make a JSON text of this JSONObject
//				.when()
//				.post("/api/users")
//				.then()
//					.statusCode(201)
//					.extract()
//					.asString();
//		
//		System.out.println("Full Response:\n" + response);
//		
//		// Parse JSON response
//		JsonPath jsonPath = new JsonPath(response);
//
//		// Extract fields directly (no "data" object here)
//		String id = jsonPath.getString("id");
//		String name = jsonPath.getString("name");
//		String job = jsonPath.getString("job");
//		String createdAt = jsonPath.getString("createdAt");
//
//		// Print extracted values
//		System.out.println("ID: " + id);
//		System.out.println("Name: " + name);
//		System.out.println("Job: " + job);
//		System.out.println("Created At: " + createdAt);
//
//		// Validations
//		Assert.assertNotNull(id, "ID should not be null");
//		Assert.assertEquals(name, "Kiran", "Name does not match");
//		Assert.assertEquals(job, "QA Engineer", "Job does not match");
	}
}

