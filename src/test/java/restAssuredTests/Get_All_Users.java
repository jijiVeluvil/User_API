package restAssuredTests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class Get_All_Users {
	
	@SuppressWarnings("unused")
	@Test
	public void getAllUsers() {
		
		RestAssured.baseURI = "https://userapi-8877aadaae71.herokuapp.com/uap";
		RestAssured.basePath = "/users";
		
		given()
		.auth().basic("Numpy@gmail.com", "tim123")
		.when()
		.get()
		.then()
		.log().body()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 ")
//Verifying Multiple contents in response body
		.body("userAddress.country", hasItems("Zimbabwe","canada","USA","india"));

	}

}
