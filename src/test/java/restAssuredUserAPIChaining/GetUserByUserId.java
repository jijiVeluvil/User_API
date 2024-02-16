package restAssuredUserAPIChaining;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetUserByUserId {
   
	@Test
	void getUserDetailsByUserId(ITestContext context) {
		int userId= (Integer) context.getAttribute("id");
		
		given()
		.auth().basic("Numpy@gmail.com", "tim123")
		.pathParam ("UserID" , userId)
		.when()
		.get("https://userapi-8877aadaae71.herokuapp.com/uap/user/{UserID}")
		.then()
		.log().body()
		.statusCode(200);
	}
}
