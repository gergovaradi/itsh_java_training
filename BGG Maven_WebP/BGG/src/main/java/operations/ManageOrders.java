package operations;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import databaseConnection.ConnetionToDB;

public class ManageOrders {

	private ConnetionToDB connection = new ConnetionToDB();
	private ResultSet rs = connection.getRs();
	private Statement st = connection.getSt();

	// Geri, 08.21 : Create a list of orders from DB data.
	public List<beans.Order> createOrderList() {
		List<beans.Order> Orders = new ArrayList<beans.Order>();

		String query = "SELECT orders.order_number,orders.date,orders.quantity,products.product_name,user_account.name"
				+ " FROM orders JOIN products ON orders.product_id = products.id"
				+ " JOIN user_account ON orders.user_id = user_account.id;";
		try {
			rs = st.executeQuery(query);
			while (rs.next()) {
				int orderNumber = rs.getInt("order_number");
				LocalDate orderDate = rs.getDate("date").toLocalDate();
				String userName = rs.getString("name");
				String productName = rs.getString("product_name");
				int quantity = rs.getInt("quantity");
				beans.Order order = new beans.Order(orderNumber, orderDate,
						userName, productName, quantity);
				Orders.add(order);
				// print line for test
				System.out.println("Order number: " + orderNumber
						+ " Orderd product: " + productName);

			}

		} catch (Exception e) {
			System.out.println("Get data from DB error: " + e.getMessage());
		}

		return Orders;
	}

	// Geri, 08.22 : save an order in DB
	public void order(int orderNumber, String userName, String productName,
			String customerName, int quantity) {

		String selectUserIdQuerry = "SELECT id FROM user_account WHERE name ="
				+ userName;
		String selectProductIdQuerry = "SELECT id FROM products WHERE product_name = "
				+ productName;
		String selectCostumerIdQuerry = "SELECT id FROM customers WHERE full_name = "
				+ customerName;

		try {
			rs = st.executeQuery(selectUserIdQuerry);
			// set the cursor to the correct place (it is default before then first row)
			rs.next();
			// getRow gives back the number of rows of resultSet, if 0 then the user is not in the DB
			if (rs.getRow() == 0) {
				// System.out.println(rs.getRow()); ---> give 0 if not in DB
				throw new RuntimeException("User not found.");
			}
			int userId = rs.getInt("id");

			rs = st.executeQuery(selectProductIdQuerry);
			rs.next();
			if (rs.getRow() == 0) {
				throw new RuntimeException("Product not found.");
			}
			int productId = rs.getInt("id");

			rs = st.executeQuery(selectCostumerIdQuerry);
			rs.next();
			if (rs.getRow() == 0) {
				throw new RuntimeException("Customer not found.");
			}
			int customerId = rs.getInt("id");
			// print line for test
			System.out.println("Uid: " + userId + ", " + "Pid: " + productId
					+ ", " + "Cid: " + customerId);

			String insertToOrdersQuerry = "INSERT INTO Orders (order_number, product_id, user_id, customer_id, quantity) VALUES("
					+ orderNumber
					+ ","
					+ productId
					+ ","
					+ userId
					+ ","
					+ customerId + "," + quantity + ")";
			st.executeUpdate(insertToOrdersQuerry);
			System.out.println("Succsesfull order");
		} catch (Exception e) {
			System.out.println("Send data to DB error: " + e.getMessage());
		}
	}

	// Geri, 08.23 : Delete a Order from DB
	public void orderDelete(int orderNumber) {
		try {
			String querry = "DELETE FROM Orders WHERE order_number = "
					+ orderNumber;
			int test = st.executeUpdate(querry);
			if (test == 0) {
				System.out.println("No order in the DB with this ID.");
				throw new RuntimeException("Not in DB.");
			}
			st.executeUpdate(querry);
			System.out
					.println("Succsesfull delete Order on this order number: "
							+ orderNumber);
		} catch (Exception e) {
			System.out.println("Delete data from DB error: " + e.getMessage());
		}
	}
	
	public void orderUpdate(int orderNumber, String userName, String productName,
			String customerName, int quantity, int id){
		
		String selectUserIdQuerry = "SELECT id FROM user_account WHERE name ="
				+ userName;
		String selectProductIdQuerry = "SELECT id FROM products WHERE product_name = "
				+ productName;
		String selectCostumerIdQuerry = "SELECT id FROM customers WHERE full_name = "
				+ customerName;	
		try {
			rs = st.executeQuery(selectUserIdQuerry);
			// set the cursor to the correct place (it is default before then first row)
			rs.next();
			// getRow gives back the number of rows of resultSet, if 0 then the user is not in the DB
			if (rs.getRow() == 0) {
				// System.out.println(rs.getRow()); ---> give 0 if not in DB
				throw new RuntimeException("User not found.");
			}
			int userId = rs.getInt("id");

			rs = st.executeQuery(selectProductIdQuerry);
			rs.next();
			if (rs.getRow() == 0) {
				throw new RuntimeException("Product not found.");
			}
			int productId = rs.getInt("id");

			rs = st.executeQuery(selectCostumerIdQuerry);
			rs.next();
			if (rs.getRow() == 0) {
				throw new RuntimeException("Customer not found.");
			}
			int customerId = rs.getInt("id");
			// print line for test
			System.out.println("Uid: " + userId + ", " + "Pid: " + productId
					+ ", " + "Cid: " + customerId);
			
			String updateToOrdersQuerry = "UPDATE Orders SET "
					+ "order_number= "+ orderNumber
					+ ","
					+ "product_id= "+ productId
					+ ","
					+ "user_id= "+ userId
					+ ","
					+ "customer_id= "+ customerId
					+","
					+ "quantity= "+ quantity + " WHERE id = " + id;
			
			st.executeUpdate(updateToOrdersQuerry);
			System.out.println("Succsesfull order updtate");
			
	}catch (Exception e) {
		System.out.println("Send data to DB error: " + e.getMessage());
	}
		};

	// main for test
	public static void main(String[] args) {
		ManageOrders orderManager = new ManageOrders();
		//orderManager.order(2, "'tom'", "'TEST_Product2'", "'Gergõ'",2);
		// orderManager.createOrderList();
		//orderManager.orderDelete(150);
		orderManager.orderUpdate(1, "'tom'", "'test_product2'", "'Gergõ Mátrai'", 20, 1);

	}

}
