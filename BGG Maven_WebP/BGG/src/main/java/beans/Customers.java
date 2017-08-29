package beans;

import java.io.Serializable;

//Geri, 08.21 : Java bean for store user informations.

public class Customers implements Serializable{
	
// Needed for serializable classes 
//explanation: https://stackoverflow.com/questions/285793/what-is-a-serialversionuid-and-why-should-i-use-it
	
	private static final long serialVersionUID = -1326315281555949485L;
	
	private String name;
	private String address;
	private String phone;
	private String email;
	
	public Customers(){
		
	};
	
	public Customers(String name, String address, String phone,
			String email) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
	
}
