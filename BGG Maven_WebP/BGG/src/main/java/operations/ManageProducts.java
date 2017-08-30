package operations;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import databaseConnection.ConnetionToDB;

public class ManageProducts {

	private ConnetionToDB connection = new ConnetionToDB();
	private ResultSet rs = connection.getRs();
	private java.sql.Connection con = connection.getCon();
	private ResourceBundle query = ResourceBundle.getBundle("querys",new Locale("hu","HU"));
	private java.sql.PreparedStatement prs;

	// Geri, 08.21 Create list of products
	public List<beans.Product> createProductList() {
		List<beans.Product> productList = new ArrayList<beans.Product>();
		try {
			prs = con.prepareStatement(query.getString("db.getAllProductdata"));
			rs = prs.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");
				// int productId = rs.getInt("id");
				beans.Product product = new beans.Product(name, price, quantity);
				productList.add(product);
				// print line for test
				System.out.println("Product name: " + name);
			}
		} catch (Exception e) {
			System.out.println("Get data from DB error: " + e.getMessage());
		}

		return productList;
	}
//get a product from DB
	public beans.Product getSpecificProduct(int id) {
		beans.Product product = null;
		try {
			prs = con.prepareStatement(query.getString("db.getAllProductdata"));
			prs.setInt(1, id);
			rs = prs.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");
				// int productId = rs.getInt("id");
				product = new beans.Product(name, price, quantity);
				
				// print line for test
				System.out.println("Product name: " + name);
			}
		} catch (Exception e) {
			System.out.println("Get data from DB error: " + e.getMessage());
		}

		return product;
	}
	// Geri, 08.23 : Execute inserts to table "Products".
	public void productRegistration(String product_name, double price,
			int quantity) {
		try {
			prs = con.prepareStatement(query.getString("db.registrateProduct"));
			prs.setString(1, product_name);
			prs.setDouble(2, price);
			prs.setInt(3, quantity);
			prs.executeUpdate();
			System.out.println("Succsesfull product registration: " + product_name);
		} catch (Exception e) {
			System.out.println("Send data to DB error: " + e.getMessage());
		}
	}

	// Geri, 08.23 : Delete a Product from DB

	public void productDelete(int ID) {
		try {
			prs = con.prepareStatement(query.getString("db.deleteProduct"));
			prs.setInt(1, ID);
			int test = prs.executeUpdate();
			if (test == 0) {
				System.out.println("No product in the DB with this ID.");
				throw new RuntimeException("Not in DB.");
			}
			else{
			prs.executeUpdate();
			System.out.println("Succsesfull delete Product on this ID: " + ID);
			}
		} catch (Exception e) {
			System.out.println("Send data to DB error: " + e.getMessage());
		}
	}

	// Geri, 08.23 : Update Product

	public void productUpdate(String product_name, double price, int quantity,
			int id) {

		try {
			prs = con.prepareStatement(query.getString("db.productUpdate"));
			prs.setString(1, product_name);
			prs.setDouble(2, price);
			prs.setInt(3, quantity);
			prs.setInt(4, id);
			prs.executeUpdate();
			System.out.println("Succsesfull update on: " + product_name);
		} catch (Exception e) {
			System.out.println("Send data to DB error: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		ManageProducts productmanager = new ManageProducts();
		//productmanager.productUpdate("testP2", 5, 1000, 4);
		//productmanager.productDelete(1);
		productmanager.productRegistration("testP3", 5, 1000);
	}

}
