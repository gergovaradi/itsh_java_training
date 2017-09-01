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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Customers customers = (Customers) o;

		if (!getName().equals(customers.getName())) return false;
		if (!getAddress().equals(customers.getAddress())) return false;
		if (!getPhone().equals(customers.getPhone())) return false;
		return getEmail().equals(customers.getEmail());
	}

	@Override
	public int hashCode() {
		int result = getName().hashCode();
		result = 31 * result + getAddress().hashCode();
		result = 31 * result + getPhone().hashCode();
		result = 31 * result + getEmail().hashCode();
		return result;
	}
}
