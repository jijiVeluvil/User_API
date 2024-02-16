package restAssuredTests;

import org.apache.commons.lang3.RandomStringUtils;

public class Rest_Utils {
	
	public static String getEmail() {
		String genaratedString = RandomStringUtils.randomAlphabetic(3);
		return (genaratedString+"gmail.com");
	}
	
	public static String getContactNum() {
		String genaratedString = RandomStringUtils.randomNumeric(10);
		return (genaratedString);
	}

}
