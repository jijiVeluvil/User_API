package restAssuredTests;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import io.restassured.RestAssured;

public class Delete_User_By_First_Name {
	
	@Test
	public void deleteUserId() {
		RestAssured.baseURI = "https://userapi-8877aadaae71.herokuapp.com/uap";
		RestAssured.basePath = "/deleteuser/username/Kanmani";
		
		Response response =
		given()
		.auth().basic("Numpy@gmail.com", "tim123")
		.when()
		.delete()
		.then()
		.statusCode(404)
		.statusLine("HTTP/1.1 404 ")
		.log().body()
		.extract().response();
		
		String jsonAsString = response.asString();
		Assert.assertEquals(jsonAsString.contains("User does not exist with name 'Kanmani'"), true);
		
		

	}

}
