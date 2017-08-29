package operations;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;





import databaseConnection.ConnetionToDB;

public class ManageCostumers {

	private ConnetionToDB connection = new ConnetionToDB();
	private ResultSet rs = connection.getRs();
	private java.sql.Connection con = connection.getCon();
	private ResourceBundle query = ResourceBundle.getBundle("querys",new Locale("hu","HU"));
	private java.sql.PreparedStatement prs;
	

	// Geri, 08.21 : Create a list of costumers from DB data.
	public List<beans.Customers> createCostumerList() {
		List<beans.Customers> UserInformationList = new ArrayList<beans.Customers>();
		try {
			prs = con.prepareStatement(query.getString("db.getAllCostumerData"));
			rs = prs.executeQuery();
			while (rs.next()) {
				String address = rs.getString("address");
				String email = rs.getString("email");
				String fullName = rs.getString("full_name");
				String phone = rs.getString("phone");
				beans.Customers customer = new beans.Customers(address,
						email, fullName, phone);
				UserInformationList.add(customer);
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
	public void customerRegistration(String fullName, String email,
			String phone, String address) {
		
		/*StringBuilder sb = new StringBuilder("INSERT INTO Customers (address, email, full_Name, phone) VALUES(");
		sb.append(address).append(",").append(email).append(",").append(fullName).append(",").append(phone).append(");");
		
		MessageFormat mformat = new MessageFormat("INSERT INTO Customers (full_name, email, phone, address) VALUES(''{0}'',''{1}'',''{2}'',''{3}'')");
		Object[] args = {fullName,email,phone,address};
		System.out.println(mformat.format(args));*/
		
		try {
			prs = con.prepareStatement( query.getString("db.registrateCostummer"));
			prs.setString(1, fullName);
			prs.setString(2, email);
			prs.setString(3, phone);
			prs.setString(4, address);
			prs.executeUpdate();
			System.out.println("Succsesfull registration.");
		} catch (Exception e) {
			System.out.println("Send data to DB error: " + e.getMessage());
		}
	}

	// Geri, 08.23 : Delete a Customer from DB
	public void customerDelete(int ID) {
		try {
			prs = con.prepareStatement( query.getString("db.deleteCostumer"));
			prs.setInt(1, ID);
			
	// check the id in DB
			int test = prs.executeUpdate();
			if (test == 0) {
				System.out.println("No customer in the DB with this ID.");
			}
			else {
			prs.executeUpdate();
			System.out.println(" Succsesfull delete Custommer: " + ID);
			}
		} catch (Exception e) {
			System.out.println("Send data to DB error: " + e.getMessage());
		}
	}

	// Geri, 08.23 : Update Customer

	public void customerUpdate(String fullName, String address, String email,
			String phone, int id) {
		try {
			prs = con.prepareStatement( query.getString("db.updateCostumer"));
			prs.setString(1, fullName);
			prs.setString(2, address);
			prs.setString(3, email);
			prs.setString(4, phone);
			prs.setInt(5, id);
			prs.executeUpdate();
			System.out.println("Succsesfull update on: " + fullName + " id: " + id);
		} catch (Exception e) {
			System.out.println("Send data to DB error: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		ManageCostumers costumermanager = new ManageCostumers();
		//costumermanager.createCostumerList();
		//costumermanager.customerRegistration("Gizi", "gizi@test.com","06304294821", "test street 52.");
		//costumermanager.customerDelete(10);
		costumermanager.customerUpdate("Gergõ Mátrai", "1191 BP","gergo.matrai@gmatrai.com", "06304892599", 1);
		
	}
}
