package restAssuredUserAPIChaining;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUserByUserId {
	 
	@Test
	void deleteUserDetailsById(ITestContext context) {
		int userId= (Integer) context.getAttribute("id");
		
		given()
		.auth().basic("Numpy@gmail.com", "tim123")
		.pathParam ("UserID" , userId)
		.when()
		.delete("https://userapi-8877aadaae71.herokuapp.com/uap/deleteuser/{UserID}")
		.then()
		.log().body()
		.statusCode(200);
	}

}
