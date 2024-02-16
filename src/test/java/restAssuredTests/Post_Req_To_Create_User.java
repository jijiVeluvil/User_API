package restAssuredTests;

import java.io.File;
import java.util.HashMap;
//import org.json.JSONObject;
import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class Post_Req_To_Create_User {
	
	@SuppressWarnings("rawtypes")
	public static HashMap map = new HashMap();
	@SuppressWarnings("rawtypes")
	public static HashMap map1 = new HashMap();
//	public static JSONArray array = new JSONArray();
	
	@BeforeClass
	@SuppressWarnings("unchecked")
	public void postData() {
		//map.put("userAddress", array.toString());
		map.put("user_first_name", "Kanmani");
		map.put("user_last_name", "TeamTen");
		map.put("user_contact_number", "5674998791");
		map.put("user_email_id", "Kanmani3@gmail.com");
		map1.put("plotNumber", "G-789");
		map1.put("street", "garang");
		map1.put("state", "MP");
		map1.put("country", "india");
		map1.put("zipCode", "784563");
		//array.put(map1);
		map.put("userAddress",map1);
		
		RestAssured.baseURI = "https://userapi-8877aadaae71.herokuapp.com/uap";
		RestAssured.basePath = "/createusers";
		
		
	}
	
	@Test
	public void testPost() {
	
		given()
		.auth().basic("Numpy@gmail.com", "tim123")
		.contentType("application/json")
		.body(map)
		.log().body()
		.when()
		.post()
		.then()
		.log().body()
		.statusCode(201)
		.statusLine("HTTP/1.1 201 ")
//Schema validation
		.assertThat()
		.body(JsonSchemaValidator
		.matchesJsonSchema(new File("C:\\Users\\renji\\eclipse-workspace\\RestAssured_User_API_Testng_BDD_FW\\schema.json")));
				

	}
}

