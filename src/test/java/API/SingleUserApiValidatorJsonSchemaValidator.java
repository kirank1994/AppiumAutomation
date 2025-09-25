package API;

import com.fasterxml.jackson.databind.ObjectMapper;
import static io.restassured.RestAssured.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

//RestAssured + json-schema-validator
public class SingleUserApiValidatorJsonSchemaValidator {

	private static final RequestSpecification requestSpec = new RequestSpecBuilder()
		.setBaseUri("https://jsonplaceholder.typicode.com")
		.setContentType(ContentType.JSON)
		.build();

	private static final ResponseSpecification responseSpec = new ResponseSpecBuilder()
		.expectStatusCode(200)
		.expectContentType(ContentType.JSON)
		.build();

	public static void main(String[] args) throws Exception {
		// Deserialize response into POJO
		User user = given()
			.spec(requestSpec)
			.when()
			.get("/users/1")
			.then()
			.spec(responseSpec)
			.body(matchesJsonSchemaInClasspath("userSchema.json")) // ✅ Schema validation
			.extract().as(User.class);

		// Pretty print
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("API Response:\n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user));

		// ✅ Business rule validations
		assertThat(user.getId(), equalTo(1));
		assertThat(user.getId(), greaterThan(0));
		assertThat(user.getEmail(), matchesPattern("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"));
		assertThat(user.getName(), not(emptyOrNullString()));
	}
}