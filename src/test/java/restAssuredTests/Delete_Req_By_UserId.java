package restAssuredTests;

//import org.json.JSONObject;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import io.restassured.RestAssured;


public class Delete_Req_By_UserId {
	@Test
	public void deleteUserId() {
		RestAssured.baseURI = "https://userapi-8877aadaae71.herokuapp.com/uap";
		RestAssured.basePath = "/deleteuser/7605";
		
		Response response =
		given()
		.auth().basic("Numpy@gmail.com", "tim123")
		.when()
		.delete()
		.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 ")
		.log().body()
		.extract().response();
		
		String jsonAsString = response.asString();
		Assert.assertEquals(jsonAsString.contains("User is deleted successfully"), true);
		
		

	}
}
