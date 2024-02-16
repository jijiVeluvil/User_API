package UserAPI_Using_JSON_File;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class UserAPIUsingJSONFile {
	int userId;
	static Response response;
	@Test(priority = 1)
	void createUser() throws FileNotFoundException {
		File f = new File ("C:\\Users\\renji\\eclipse-workspace"
				+ "\\RestAssured_User_API_Testng_Testng_FW\\user_API.json");
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);
		response = 
				 given().auth().basic("Numpy@gmail.com", "tim123")
				.contentType("application/json")
				.body(data.toString())
				.log().body()
				.when()
				.post("https://userapi-8877aadaae71.herokuapp.com/uap/createusers")
				.andReturn();
		userId = response.getBody().jsonPath().getInt("user_id");
		Assert.assertEquals(response.getStatusCode(), 201);
		System.out.println("Response Time " + response.getTime());
		System.out.println("Content Type " + response.getContentType());
		System.out.println("Body " +response.getBody().asString());
		System.out.println("Status Line " +response.getStatusLine());
		System.out.println("User Id " +userId);
	}
	
	@Test(priority = 2)
	void getUserByUserId() {
		given()
		.auth().basic("Numpy@gmail.com", "tim123")
		.when()
		.get("https://userapi-8877aadaae71.herokuapp.com/uap/user/" + userId).then().statusCode(200);

    }
	@Test(priority = 3)
	void getUserByUserName() {
		given()
		.auth().basic("Numpy@gmail.com", "tim123")
		.when()
		.get("https://userapi-8877aadaae71.herokuapp.com/uap/users/username/Kanmani")
		.then()
		.log().body()
		.statusCode(200);

	}
    
	@Test(priority = 4)
	void deleteUserByUserId() {
		given()
		.auth().basic("Numpy@gmail.com", "tim123")
		.when()
		.delete("https://userapi-8877aadaae71.herokuapp.com/uap/deleteuser/" + userId).then().statusCode(200);
	}
	
	@Test(priority = 6)
	void deleteUserByFirstName() {
		given()
		.auth().basic("Numpy@gmail.com", "tim123")
		.when()
		.delete("https://userapi-8877aadaae71.herokuapp.com/uap/deleteuser/username/Kanmani")
		.then()
	    .statusCode(404);
	}
	}

	
	


