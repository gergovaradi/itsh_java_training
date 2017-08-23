package operations;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import databaseConnection.ConnetionToDB;

public class ManageProducts {

	private ConnetionToDB connection = new ConnetionToDB();
	private ResultSet rs = connection.getRs();
	private Statement st = connection.getSt();

	// Geri, 08.21 Create list of products
	public List<beans.Product> createProductList() {
		List<beans.Product> productList = new ArrayList<beans.Product>();
		try {
			String query = "SELECT * FROM Products";
			rs = st.executeQuery(query);
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

	// Geri, 08.23 : Execute inserts to table "Products".
	public void productRegistration(String product_name, double price,
			int quantity) {

		String querry = "INSERT INTO Products (product_name, price, quantity) VALUES("
				+ product_name + "," + price + "," + quantity + ");";
		try {
			st.executeUpdate(querry);
			System.out.println("Succsesfull product registration: "
					+ product_name);
		} catch (Exception e) {
			System.out.println("Send data to DB error: " + e.getMessage());
		}
	}

	// Geri, 08.23 : Delete a Product from DB

	public void productDelete(int ID) {
		try {
			String querry = "DELETE FROM Products WHERE id = " + ID;
			int test = st.executeUpdate(querry);
			if (test == 0) {
				System.out.println("No product in the DB with this ID.");
				throw new RuntimeException("Not in DB.");
			}
			st.executeUpdate(querry);
			System.out.println("Succsesfull delete Product: " + ID);
		} catch (Exception e) {
			System.out.println("Send data to DB error: " + e.getMessage());
		}
	}

	// Geri, 08.23 : Update Product

	public void productUpdate(String product_name, double price, int quantity,
			int id) {

		String querry = "UPDATE Products SET product_name = " + product_name
				+ ", " + "price = " + price + "," + "quantity = " + quantity
				+ " WHERE id = " + id;

		try {
			st.executeUpdate(querry);
			System.out.println("Succsesfull update on: " + product_name);
		} catch (Exception e) {
			System.out.println("Send data to DB error: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		ManageProducts productmanager = new ManageProducts();
		// productmanager.productUpdate("'TEST_Product2'", 6, 500, 1);
		productmanager.productDelete(1);
	}

}
