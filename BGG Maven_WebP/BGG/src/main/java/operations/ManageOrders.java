package operations;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import databaseConnection.ConnetionToDB;

public class ManageOrders {

	private ConnetionToDB connection = new ConnetionToDB();
	private ResultSet rs = connection.getRs();
	private Statement st = connection.getSt();
	private java.sql.Connection con = connection.getCon();
	private ResourceBundle query = ResourceBundle.getBundle("querys",new Locale("hu","HU"));
	private java.sql.PreparedStatement prs;
	
	// Geri, 08.21 : Create a list of orders from DB data.
	public List<beans.Order> createOrderList() {
		List<beans.Order> Orders = new ArrayList<beans.Order>();

		try {
			prs = con.prepareStatement(query.getString("db.getAllOrderData"));
			rs = prs.executeQuery();
			while (rs.next()) {
				int orderNumber = rs.getInt("order_number");
				LocalDate orderDate = rs.getDate("date").toLocalDate();
				String userName = rs.getString("name");
				String productName = rs.getString("product_name");
				int quantity = rs.getInt("quantity");
				int order_id = rs.getInt("id");
				beans.Order order = new beans.Order(orderNumber, orderDate,
						userName, productName, quantity,order_id);
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

	// Geri, 08.22 : save a new order in DB
	public void order(int orderNumber, String userName, String productName,
			String customerName, int quantity) {

		try {
			prs = con.prepareStatement(query.getString("selectUserIdQuerry"));
			rs = prs.executeQuery();
			// set the cursor to the correct place (it is default before then first row)
			rs.next();
			// getRow gives back the number of rows of resultSet, if 0 then the user is not in the DB
			if (rs.getRow() == 0) {
				// System.out.println(rs.getRow()); ---> give 0 if not in DB
				throw new RuntimeException("User not found.");
			}
			int userId = rs.getInt("id");

			prs = con.prepareStatement(query.getString("db.selectUserIdQuerry"));
			
			rs = prs.executeQuery();
			rs.next();
			if (rs.getRow() == 0) {
				throw new RuntimeException("Product not found.");
			}
			int productId = rs.getInt("id");

			prs = con.prepareStatement(query.getString("db.selectCostumerIdQuerry"));
			rs = prs.executeQuery();
			rs.next();
			if (rs.getRow() == 0) {
				throw new RuntimeException("Customer not found.");
			}
			int customerId = rs.getInt("id");
			// print line for test
			System.out.println("Uid: " + userId + ", " + "Pid: " + productId
					+ ", " + "Cid: " + customerId);
			prs = con.prepareStatement("db.insertToOrdersQuerry");
			prs.setString(1, fullName);
			prs.setString(2, email);
			prs.setString(3, phone);
			prs.setString(4, address);
			st.executeUpdate(insertToOrdersQuerry);
			System.out.println("Succsesfull order");
			
			// Decrease the quantity in the inventory:
			updateQuantityOrder(quantity, productId);
			
		} catch (Exception e) {
			System.out.println("Send data to DB error: " + e.getMessage());
		}
		
	}
	
	// Geri, 08.24 method for decrease the quantity in db when some one order a product		
	private void updateQuantityOrder(int quantity, int product_id){
		
		int quantityInDB;
		String getProductQuantityQuerry ="SELECT quantity FROM products WHERE id=" + product_id;
		
		try {
			rs = st.executeQuery(getProductQuantityQuerry);
			rs.next();
			quantityInDB = rs.getInt("quantity");
			
			String updateQuerry = "UPDATE Products SET " + "quantity = " + (quantityInDB - quantity)
					+ " WHERE id = " +  product_id;
			st.executeUpdate(updateQuerry);
			
			System.out.println("Succsesfull update");
		} catch (Exception e) {
			System.out.println("Update data DB error: " + e.getMessage());
		}
	}

	// Geri, 08.23 : Delete a Order from DB
	public void orderDelete(int orderNumber) {
		updateQuantityDeleteOrder(orderNumber);
		try {
			String querry = "DELETE FROM Orders WHERE order_number = "
					+ orderNumber;
			int test = st.executeUpdate(querry);
			if (test == 0) {
				System.out.println("No order in the DB with this ID.");
				throw new RuntimeException("Not in DB.");
			}
			st.executeUpdate(querry);
			System.out.println("Succsesfull delete Order on this order number: "
							+ orderNumber);
		} catch (Exception e) {
			System.out.println("Delete data from DB error: " + e.getMessage());
		}
	}
	
	// Geri, 08.24 method for increase the quantity in db when some one delete an order.		
	private void updateQuantityDeleteOrder(int order_number){
		
		List<beans.Order> Orders = createOrderList();
		List<Integer> OrderdProducts = new ArrayList<Integer>() ;
		int quantityInOrder;
		int quantityInDB;
		
		for (beans.Order order : Orders){
			if(order.getOrderNumber() == order_number){
				OrderdProducts.add(order.getOrder_id());
			}
		for(int id : OrderdProducts){
			String getOrderQuantityQuerry ="SELECT quantity FROM orders WHERE id=" + id;
			String getProductQuantityQuerry = "SELECT quantity FROM products WHERE id= (SELECT product_id FROM orders WHERE id= "+id +")";
			try {
				rs = st.executeQuery(getOrderQuantityQuerry);
				rs.next();
				quantityInOrder = rs.getInt("quantity");
				
				rs = st.executeQuery(getProductQuantityQuerry);
				rs.next();
				quantityInDB = rs.getInt("quantity");
				
				String updateQuerry = "UPDATE Products SET " + "quantity = " + (quantityInDB + quantityInOrder)
						+ " WHERE id = " + "(SELECT product_id FROM orders WHERE id= "+id +")";
				st.executeUpdate(updateQuerry);
				
				
			} catch (Exception e) {
				System.out.println("Delete data DB error: " + e.getMessage());
			}
		}
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
		//orderManager.order(6, "'tom'", "'testP2'", "'Gizi'",200);
		orderManager.createOrderList();
		//orderManager.orderDelete(6);
		//orderManager.orderUpdate(5, "'tom'", "'testP1'", "'Gizi'", 5, 1000);

	}

}
