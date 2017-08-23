package operations;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import databaseConnection.ConnetionToDB;

public class ManageCostumers {

	private ConnetionToDB connection = new ConnetionToDB();
	private ResultSet rs = connection.getRs();
	private Statement st = connection.getSt();

	// Geri, 08.21 : Create a list of costumers from DB data.
	public List<beans.Customers> createCostumerList() {
		List<beans.Customers> UserInformationList = new ArrayList<beans.Customers>();
		try {
			String query = "SELECT * FROM Customers";
			rs = st.executeQuery(query);
			while (rs.next()) {
				String address = rs.getString("address");
				String email = rs.getString("email");
				String fullName = rs.getString("full_name");
				String phone = rs.getString("phone");
				beans.Customers userInformation = new beans.Customers(address,
						email, fullName, phone);
				UserInformationList.add(userInformation);
				// print line for test
				System.out.println("Costumer phone-number: " + phone + " ");
			}
		} catch (Exception e) {
			System.out.println("Get data from DB error: " + e.getMessage());
		}

		return UserInformationList;
	}

	// Geri, 08.21 : Execute inserts to table "Customers" when a user register a
	// customer.
	// IMPORTANT : When we get a String from front-end we should concat between
	// '+..+' like this: 'example'
	public void customerRegistration(String fullName, String email,
			String phone, String address) {
		try {
			String querry = "INSERT INTO Customers (address, email, full_Name, phone) VALUES("
					+ address
					+ ","
					+ email
					+ ","
					+ fullName
					+ ","
					+ phone
					+ ");";
			st.executeUpdate(querry);
			System.out.println("Succsesfull registration.");
		} catch (Exception e) {
			System.out.println("Send data to DB error: " + e.getMessage());
		}
	}

	// Geri, 08.23 : Delete a Customer from DB
	public void customerDelete(int ID) {
		try {
			String querry = "DELETE FROM Customers WHERE id = " + ID;
			int test = st.executeUpdate(querry);
			if (test == 0) {
				System.out.println("No customer in the DB with this ID.");
				throw new RuntimeException("Not in DB.");
			}
			st.executeUpdate(querry);
			System.out.println(" Succsesfull delete Custommer: " + ID);
		} catch (Exception e) {
			System.out.println("Send data to DB error: " + e.getMessage());
		}
	}

	// Geri, 08.23 : Update Customer

	public void customerUpdate(String full_name, String address, String email,
			String phone, int id) {

		String querry = "UPDATE Customers SET full_name = " + full_name + ", "
				+ "address = " + address + ", " + "email = " + email + ","
				+ "phone = " + phone + " WHERE id = " + id;

		try {
			st.executeUpdate(querry);
			System.out.println("Succsesfull update on: " + full_name + " id: " + id);
		} catch (Exception e) {
			System.out.println("Send data to DB error: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		ManageCostumers costumermanager = new ManageCostumers();
		// costumermanager.createCostumerList();
		// costumermanager.customerRegistration("'Gizi'", "'gizi@test.com'","'11231414'", "'test street 52.'");
		//costumermanager.customerDelete(2);
		costumermanager.customerUpdate("'Gergõ Mátrai'", "'1191 BP'","'gergo.matrai@gmatrai.com'", "'06304892599'", 1);
	}
}
