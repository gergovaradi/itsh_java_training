package operations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import databaseConnection.ConnetionToDB;


public class ManageCustomers {

	private ConnetionToDB connection = new ConnetionToDB();
	private ResourceBundle query = ResourceBundle.getBundle("querys",new Locale("hu","HU"));
	private static final Logger logger = LogManager.getLogger(ManageCustomers.class);
	

	
	public List<beans.Customers> createCostumerList() {
		logger.info("Entering the createCostumerList method");

		List<beans.Customers> UserInformationList = new ArrayList<beans.Customers>();
		
		try(Connection con = connection.openConnection();
			PreparedStatement prs = con.prepareStatement(query.getString("db.getAllCostumerdata"));
			ResultSet rs = prs.executeQuery();	
			){
			
			while (rs.next()) {
				String address = rs.getString("address");
				String email = rs.getString("email");
				String fullName = rs.getString("full_name");
				String phone = rs.getString("phone");
				beans.Customers customer = new beans.Customers(address,
						email, fullName, phone);
				UserInformationList.add(customer);
				// print line for test
				System.out.println("pN: " + phone);
				logger.info("print line for test: Costumer phonenumber: " + phone + " ");
			}
		} catch (Exception e) {
			logger.error("Get data from DB error: " + e.getMessage());
		}

		return UserInformationList;
		
	}

	
	public void customerRegistration(String fullName, String email,
			String phone, String address) {
				
		try(Connection con = connection.openConnection();
			PreparedStatement prs = con.prepareStatement( query.getString("db.registrateCostummer"));
				) {	
			prs.setString(1, fullName);
			prs.setString(2, email);
			prs.setString(3, phone);
			prs.setString(4, address);
			prs.executeUpdate();
			logger.info("Succsesful registration.");
		} catch (Exception e) {
			logger.error("Send data to DB error: " + e.getMessage());
		}
	}

	public void customerDelete(int ID) {
		try(Connection con = connection.openConnection();
			PreparedStatement prs = con.prepareStatement( query.getString("db.deleteCostumer"));
				) {		
			prs.setInt(1, ID);
			
	// check the id in DB
			int test = prs.executeUpdate();
			if (test == 0) {
				logger.error("No customer in the DB with this ID.");
			}
			else {
			prs.executeUpdate();
			logger.info("Succsesfully deleted Custommer: " + ID);
			}
		} catch (Exception e) {
			logger.error("Send data to DB error: " + e.getMessage());
		}
	}

	
	public void customerUpdate(String fullName, String address, String email,
			String phone, int id) {
		try(Connection con = connection.openConnection();
			PreparedStatement prs = con.prepareStatement( query.getString("db.updateCostumer"));	
			){
			prs.setString(1, fullName);
			prs.setString(2, address);
			prs.setString(3, email);
			prs.setString(4, phone);
			prs.setInt(5, id);
			prs.executeUpdate();
			logger.info("Succsesful update on: " + fullName + " id: " + id);
		} catch (Exception e) {
			logger.error("Send data to DB error: " + e.getMessage());
		}
	}
	

	public static void main(String[] args) {
		ManageCustomers costumermanager = new ManageCustomers();
		//costumermanager.createCostumerList();
		//costumermanager.customerRegistration("Gizi", "gizi@test.com","06304294821", "test street 52.");
		//costumermanager.customerDelete(12);
		costumermanager.customerUpdate("Gergo Matrai", "1191 BP","gergo.matrai@gmatrai.com", "0612793677", 1);
		
	}
}
