package restAssuredTests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

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
		.assertThat().body("user_id[0]", equalTo(7535))
		.header("Content-Type", "application/json");

	}

}
