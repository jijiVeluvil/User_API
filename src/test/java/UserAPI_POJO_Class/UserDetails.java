package UserAPI_POJO_Class;

import java.util.HashMap;
import java.util.List;

public class UserDetails {
	public String user_first_name;
	public String user_last_name;
	public String user_contact_number;
	public String user_email_id;
	
	public HashMap<String,Object> userAddress;
	
	public String getUserFirstName() {
		return user_first_name;
	}
	public void setUserFirstName(String user_first_name) {
		this.user_first_name = user_first_name;
	}
	public String getUserLastName() {
		return user_last_name; 
	}
	public void setUserLastName(String user_last_name) {
		this.user_last_name = user_last_name;
	}
	public String getUserContactNum() {
		return user_contact_number;
	}
	public void setUserContactNum(String user_contact_number) {
		this.user_contact_number = user_contact_number;
	}
	public String getUserEmailId() {
		return user_email_id;
	}
	public void setUserEmailId(String user_email_id) {
		this.user_email_id = user_email_id;
	}
	
	public HashMap<String,Object> getUserAddress () {
		return userAddress;
	}
	public void setUserAddress(HashMap<String,Object> userAddress) {
		this.userAddress = userAddress;
	}
	

}
