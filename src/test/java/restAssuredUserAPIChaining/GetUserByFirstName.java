package restAssuredUserAPIChaining;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class GetUserByFirstName {
	
	public class Get_Req_By_First_Name {
		@Test
		public void userDetailsByFirstname() {
			given()
			.auth().basic("Numpy@gmail.com", "tim123")
			.when()
			.get("https://userapi-8877aadaae71.herokuapp.com/uap/users/username/Kanmani")
			.then()
			.log().body()
			.statusCode(200)
			.statusLine("HTTP/1.1 200 ")
			.header("Content-Type", "application/json");

		}

	}


}
