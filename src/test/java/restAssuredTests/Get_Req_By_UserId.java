package restAssuredTests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get_Req_By_UserId {

	@Test
	public void userDetails() {
		given()
		.auth().basic("Numpy@gmail.com", "tim123")
		.when()
		.get("https://userapi-8877aadaae71.herokuapp.com/uap/user/7605")
		.then()
		.log().body()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 ")
		.assertThat().body("user_id", equalTo(7605))
		.body("user_first_name",equalTo("Kanmani"))
		.body("userAddress.street",equalTo("garang"))
		.header("Content-Type", "application/json");

	}

}
