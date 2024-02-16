package dataDrivenUserAPIUsingExcel;

import java.io.IOException;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DatadrivenTest {
	int userId;
	@Test(priority = 1,dataProvider = "userdataprovider")
	void postNewUser(String userFirstName,String userLastName,String userConNum,String userEmail,String plotNum,
			String strt,String stat,String cntry,String zipCde) {
		
		RestAssured.baseURI = "https://userapi-8877aadaae71.herokuapp.com/uap";
		RequestSpecification httpRequest = RestAssured.given().auth().basic("Numpy@gmail.com", "tim123");
		
		//Here we create a data which we can send along with the request
		JSONObject data = new JSONObject();
		JSONObject data1 = new JSONObject();
		data.put ("user_first_name",userFirstName);
		data.put("user_last_name", userLastName);
		data.put("user_contact_number",userConNum );
		data.put("user_email_id", userEmail);
		data1.put("plotNumber", plotNum);
		data1.put("street", strt);
		data1.put("state", stat);
		data1.put("country", cntry);
		data1.put("zipCode", zipCde);
		data.put("userAddress",data1);
		
		//Add a header stating the request body is a json
		httpRequest.header("Content-Type","application/json");
		//Add json to the body of the request
		httpRequest.body(data.toString());
		//Post Request
		Response response = httpRequest.request(Method.POST,"/createusers");
		userId = response.getBody().jsonPath().getInt("user_id");
		String responseBody = response.getBody().asString();
		//System.out.println(responseBody);
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode,201);
		
	}
	@Test(priority = 2,dependsOnMethods = { "postNewUser" })
	void getUserById( ) {
		RestAssured.baseURI = "https://userapi-8877aadaae71.herokuapp.com/uap";
		RequestSpecification httpRequest = RestAssured.given().auth().basic("Numpy@gmail.com", "tim123");
		Response response = httpRequest.request(Method.GET,"/user/"+userId);
		System.out.println(userId);
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		
	}
	@Test(priority = 3,dependsOnMethods = { "postNewUser" })
	void deleteUserById( ) {
		RestAssured.baseURI = "https://userapi-8877aadaae71.herokuapp.com/uap";
		RequestSpecification httpRequest = RestAssured.given().auth().basic("Numpy@gmail.com", "tim123");
		Response response = httpRequest.request(Method.DELETE,"/deleteuser/"+userId);
		System.out.println(userId);    
	}
		
		@DataProvider(name="userdataprovider")
		String[][] getUserData() throws IOException {
			String path = System.getProperty("user.dir")+"/src/test/java/dataDrivenUserAPIUsingExcel/User_API.xlsx";
			int rowNum = XLUtils.getRowCount(path, "Sheet1");
			int colCount = XLUtils.getCellCount(path, "Sheet1", 1);
			String userData[][] = new String [rowNum][colCount];
			for(int i = 1; i <= rowNum; i++) {
				for(int j = 0; j < colCount; j++) {
					userData [i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
				}
			}
			
			return userData;	 
			
		}
	

}
