package UserAPI_POJO_Class;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserDetailsUsingPOJOClass {
	
	int userId ;
	static Response response;
	String user_first_name;
	
	@Test(priority = 1)
	public void createUser() {
		HashMap<String,Object> AdressList = new HashMap<String,Object>();
		AdressList.put("plotNumber", "G-789");
		AdressList.put("street", "garang");
		AdressList.put("state", "MP");
		AdressList.put("country", "india");
		AdressList.put("zipCode", "784563");
		
		UserDetails UD = new UserDetails();
		UD.setUserFirstName("Kanmani");
		UD.setUserLastName("TeamTen");
		UD.setUserContactNum("5674328791");
		UD.setUserEmailId("Kanmani0@gmail.com");
		UD.setUserAddress(AdressList);
		
		RestAssured.baseURI = "https://userapi-8877aadaae71.herokuapp.com/uap";
		RestAssured.basePath = "/createusers";
		response = 
				given().auth().basic("Numpy@gmail.com", "tim123")
				.contentType("application/json")
				.body(UD)
				.log().body()
				.when()
				.post()
				.andReturn();
	    userId = response.getBody().jsonPath().getInt("user_id");
	    user_first_name = response.getBody().jsonPath().getString("user_first_name");
	}	
		@Test(priority = 2)
		public void getUserByUserId() {
			
				given().
				auth().basic("Numpy@gmail.com", "tim123")
				.when()
				.get("https://userapi-8877aadaae71.herokuapp.com/uap/user/" + userId)
				.then().statusCode(200);
        }
		

		@Test(priority = 3)
		void getUserByFirstname() {
			given()
			.auth()
			.basic("Numpy@gmail.com", "tim123")
			.when()
			.get("https://userapi-8877aadaae71.herokuapp.com/uap/users/username/"+user_first_name)
			.then().log().body()
			.statusCode(200);
		}
		
		@Test(priority = 4)
		void updateUser() {
			HashMap<String,Object> AdressList = new HashMap<String,Object>();
			AdressList.put("plotNumber", "G-789");
			AdressList.put("street", "garang");
			AdressList.put("state", "MP");
			AdressList.put("country", "usa");
			AdressList.put("zipCode", "784563");
			
			UserDetails UD = new UserDetails();
			UD.setUserFirstName("Kanmani");
			UD.setUserLastName("Mol");
			UD.setUserContactNum("5674328791");
			UD.setUserEmailId("Kanmani0@gmail.com");
			UD.setUserAddress(AdressList);
			
			given()
			.auth().basic("Numpy@gmail.com", "tim123")
			.contentType("application/json")
			.body(UD).log().body()
			.when()
			.put("https://userapi-8877aadaae71.herokuapp.com/uap/updateuser/" + userId)
			.then().statusCode(200);
        }
		
		@Test(priority = 5)
		void deleteUserByUserId() {
			given()
			.auth()
			.basic("Numpy@gmail.com", "tim123")
			.when()
			.delete("https://userapi-8877aadaae71.herokuapp.com/uap/deleteuser/" + userId)
			.then()
			.statusCode(200);
		}

		@Test(priority = 6)
		void deleteUserByFirstName() {
			given()
			.auth()
			.basic("Numpy@gmail.com", "tim123")
			.when()
			.delete("https://userapi-8877aadaae71.herokuapp.com/uap/deleteuser/username/"+user_first_name).then()
			.statusCode(404);
		}

}
