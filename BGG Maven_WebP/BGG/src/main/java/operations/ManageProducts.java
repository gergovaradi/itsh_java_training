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

public class ManageProducts {

	private ConnetionToDB connection = new ConnetionToDB();
	private ResourceBundle query = ResourceBundle.getBundle("querys",new Locale("hu","HU"));
	private static final Logger logger = LogManager.getLogger(ManageProducts.class);


	public List<beans.Product> createProductList() {
		List<beans.Product> productList = new ArrayList<beans.Product>();
		
		try(Connection con = connection.openConnection();
			PreparedStatement prs = con.prepareStatement(query.getString("db.getAllProductdata"));
			ResultSet rs = prs.executeQuery();	
			) {
			
			while (rs.next()) {
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");
				beans.Product product = new beans.Product(name, price, quantity);
				productList.add(product);
				logger.info("print line for test: Product name: " + name);
			}
		} catch (Exception e) {
			logger.error("Get data from DB error: " + e.getMessage());
		}

		return productList;
	}
//get a product from DB
	public beans.Product getSpecificProduct(int id) {
		beans.Product product = null;
		try(Connection con = connection.openConnection();
			PreparedStatement prs = con.prepareStatement(query.getString("db.getAllProductdata"));
			ResultSet rs = prs.executeQuery();
			){
			
			prs.setInt(1, id);
			
			while (rs.next()) {
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");
				product = new beans.Product(name, price, quantity);
				
				logger.info("print line for test: Product name: " + name);
			}
		} catch (Exception e) {
			logger.error("Get data from DB error: " + e.getMessage());
		}

		return product;
	}

	public void productRegistration(String product_name, double price,
			int quantity) {
		try (Connection con = connection.openConnection();
			 PreparedStatement prs = con.prepareStatement(query.getString("db.registrateProduct"));
			){
			prs.setString(1, product_name);
			prs.setDouble(2, price);
			prs.setInt(3, quantity);
			prs.executeUpdate();
			logger.info("Successful product registration: " + product_name);
		} catch (Exception e) {
			logger.error("Send data to DB error: " + e.getMessage());
		}
	}



	public void productDelete(int ID) {
		try(Connection con = connection.openConnection();
			PreparedStatement prs = con.prepareStatement(query.getString("db.deleteProduct"));	
			) {
			
			prs.setInt(1, ID);
			int test = prs.executeUpdate();
			if (test == 0) {
				logger.warn("No product in the DB with this ID.");
				throw new RuntimeException("Not in DB.");
			}
			else{
			prs.executeUpdate();
			logger.info("Successfully deleted Product on this ID: " + ID);
			}
		} catch (Exception e) {
			logger.error("Send data to DB error: " + e.getMessage());
		}
	}



	public void productUpdate(String product_name, double price, int quantity,
			int id) {

		try(Connection con = connection.openConnection();
			PreparedStatement prs = con.prepareStatement(query.getString("db.productUpdate"));
			){
			
			prs.setString(1, product_name);
			prs.setDouble(2, price);
			prs.setInt(3, quantity);
			prs.setInt(4, id);
			prs.executeUpdate();
			logger.info("Successful update on: " + product_name);
		} catch (Exception e) {
			logger.error("Send data to DB error: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		ManageProducts productmanager = new ManageProducts();
		productmanager.productUpdate("testP3", 5, 1000, 4);
		//productmanager.productDelete(7);
		//productmanager.productRegistration("testP5", 5, 1000);
	}

}
