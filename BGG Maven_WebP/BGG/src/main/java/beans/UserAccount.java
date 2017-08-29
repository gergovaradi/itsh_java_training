package beans;

import java.io.Serializable;

//Geri, 08.21 : Java bean for store user accounts.

	public class UserAccount implements Serializable {
	

		private static final long serialVersionUID = 80984795963057557L;
	
		private String userName;
		private String password;
		private String salt;
		
	public UserAccount(){
		
	}	
	
	public UserAccount(String userName, String password, String salt) {
		super();
		this.userName = userName;
		this.password = password;
		this.salt = salt;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	

}
