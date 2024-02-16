package restAssuredUserAPI;

import java.io.File;
import java.util.HashMap;
//import org.json.JSONObject;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
//import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class AllRequests {
	int userId;
	static Response response;
    String user_first_name;

	@Test(priority = 1)
	void getUsers() {
		given().auth().basic("Numpy@gmail.com", "tim123").when()
				.get("https://userapi-8877aadaae71.herokuapp.com/uap/users").then().statusCode(200);
	}

	@Test(priority = 2)
	void createUser() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map.put("user_first_name", "Kanmani");
		map.put("user_last_name", "TeamTen");
		map.put("user_contact_number", "5674998791");
		map.put("user_email_id", "Kanmani3@gmail.com");
		map1.put("plotNumber", "G-789");
		map1.put("street", "garang");
		map1.put("state", "MP");
		map1.put("country", "india");
		map1.put("zipCode", "784563");
		// array.put(map1);
		map.put("userAddress", map1);

		RestAssured.baseURI = "https://userapi-8877aadaae71.herokuapp.com/uap";
		RestAssured.basePath = "/createusers";

		response = 
				given().auth().basic("Numpy@gmail.com", "tim123")
				.contentType("application/json")
				.body(map)
				.log().body()
				.when()
				.post()
				.andReturn();
		userId = response.getBody().jsonPath().getInt("user_id");
		user_first_name = response.getBody().jsonPath().getString("user_first_name");

		Assert.assertEquals(response.getStatusCode(), 201);
		System.out.println("Response Time " + response.getTime());
		System.out.println("Content Type " + response.getContentType());
		System.out.println("Body " +response.getBody().asString());
		System.out.println("Status Line " +response.getStatusLine());
		System.out.println("User Id " +userId);

	}

	@Test(priority = 3)
	void getUserByUserId() {
		given().auth().basic("Numpy@gmail.com", "tim123").when()
				.get("https://userapi-8877aadaae71.herokuapp.com/uap/user/" + userId).then().statusCode(200);

	}

	@Test(priority = 4)
	void getUserByFirstname() {
		given().auth().basic("Numpy@gmail.com", "tim123").when()
				.get("https://userapi-8877aadaae71.herokuapp.com/uap/users/username/"+user_first_name).then().log().body()
				.statusCode(200);

	}

	@Test(priority = 4, dependsOnMethods = { "createUser" })
	void updateUser() {
		HashMap<String,Object> map = new HashMap<String,Object>();
		HashMap<String,Object> map1 = new HashMap<String,Object>();
		map.put("user_first_name", "Kanmani");
		map.put("user_last_name", "Mole");
		map.put("user_contact_number", "5674998791");
		map.put("user_email_id", "Kanmani3@gmail.com");
		map1.put("plotNumber", "G-789");
		map1.put("street", "garang");
		map1.put("state", "MP");
		map1.put("country", "india");
		map1.put("zipCode", "784563");
		// array.put(map1);
		map.put("userAddress", map1);

		given().auth().basic("Numpy@gmail.com", "tim123").contentType("application/json").body(map).log().body().when()
				.put("https://userapi-8877aadaae71.herokuapp.com/uap/updateuser/" + userId).then().statusCode(200);

	}

	@Test(priority = 5)
	void deleteUserByUserId() {
		given().auth().basic("Numpy@gmail.com", "tim123").when()
				.delete("https://userapi-8877aadaae71.herokuapp.com/uap/deleteuser/" + userId).then().statusCode(200);
	}

	@Test(priority = 6)
	void deleteUserByFirstName() {
		given().auth().basic("Numpy@gmail.com", "tim123").when()
				.delete("https://userapi-8877aadaae71.herokuapp.com/uap/deleteuser/username/"+user_first_name).then()
				.statusCode(404);
	}

}