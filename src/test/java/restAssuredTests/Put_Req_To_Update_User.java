package restAssuredTests;

import java.util.HashMap;
import org.json.JSONArray;
//import org.json.JSONObject;
import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class Put_Req_To_Update_User {
	
	@SuppressWarnings("rawtypes")
	public static HashMap map = new HashMap();
	@SuppressWarnings("rawtypes")
	public static HashMap map1 = new HashMap();
	public static JSONArray array = new JSONArray();
	
	@BeforeClass
	@SuppressWarnings("unchecked")
	public void postData() {
		//map.put("userAddress", array);
		map.put("user_first_name", "Kanmani");
		map.put("user_last_name", "Mole");
		map.put("user_contact_number", "5674998791");
		map.put("user_email_id", "Kanmani3@gmail.com");
		map1.put("plotNumber", "G-789");
		map1.put("street", "garang");
		map1.put("state", "MP");
		map1.put("country", "india");
		map1.put("zipCode", "784563");
		map.put("userAddress",map1);
		//array.put(map1);
		
		
		RestAssured.baseURI = "https://userapi-8877aadaae71.herokuapp.com/uap";
		RestAssured.basePath = "/updateuser/7605";
	}
	
	@Test
	public void testPost() {
		given()
		.auth().basic("Numpy@gmail.com", "tim123")
		.contentType("application/json")
		.body(map)
		.log().body()
		.when()
		.put()
		.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 ");
		
//		System.out.println(map.toString());
	}
	

}
