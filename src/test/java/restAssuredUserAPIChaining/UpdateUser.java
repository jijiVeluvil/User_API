package restAssuredUserAPIChaining;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class UpdateUser {
	
	@Test
	 void updateUserDetails(ITestContext context) {
		 JSONObject data = new JSONObject();
			JSONObject data1 = new JSONObject();
			data.put ("user_first_name","Kanmani");
			data.put("user_last_name", "Mol");
			data.put("user_contact_number", "5674998791");
			data.put("user_email_id", "Kanmani3@gmail.com");
			data1.put("plotNumber", "G-789");
			data1.put("street", "garang");
			data1.put("state", "MP");
			data1.put("country", "india");
			data1.put("zipCode", "784563");
			data.put("userAddress",data1);
			
			int userId= (Integer) context.getAttribute("id");
			
		 given()
			.auth().basic("Numpy@gmail.com", "tim123")
			.contentType("application/json")
			.pathParam ("UserID" , userId)
			.body(data.toString())
			.log().body()
			.when()
			.put("https://userapi-8877aadaae71.herokuapp.com/uap/updateuser/{UserID}")
			.then()
			.log().body()
			.statusCode(200);
		System.out.println("UserId "+userId);
			
	 }

}
