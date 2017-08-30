package operations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	private static final Logger logger = LogManager.getLogger(ManageOrders.class);
	
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
				logger.info("print line for test: Order number: " + orderNumber + " Orderd product: " + productName);
			}
		} catch (Exception e) {
			logger.error("Get data from DB error: " + e.getMessage());
		}

		return Orders;
	}

	// Geri, 08.22 : save a new order in DB
	public void order(int orderNumber, String userName, String productName,
			String customerName, int quantity) {

		try {
			prs = con.prepareStatement(query.getString("db.selectUserIdQuery"));
			prs.setString(1, userName);
			rs = prs.executeQuery();
			int userId = 0;
			// set the cursor to the correct place (it is default before then first row)
			rs.next();
			// getRow gives back the number of rows of resultSet, if 0 then the user is not in the DB
			if (rs.getRow() == 0) {
				throw new RuntimeException("User not found.");
			}
			userId = rs.getInt("id");

			prs = con.prepareStatement(query.getString("db.selectProductIdQuery"));
			prs.setString(1, productName);
			rs = prs.executeQuery();
			int productId = 0;
			rs.next();
			if (rs.getRow() == 0) {
				throw new RuntimeException("Product not found.");
			}
			productId = rs.getInt("id");

			prs = con.prepareStatement(query.getString("db.selectCostumerIdQuery"));
			prs.setString(1, customerName);
			rs = prs.executeQuery();
			int customerId = 0;
			rs.next();
			if (rs.getRow() == 0) {
				throw new RuntimeException("Customer not found.");
			}
			customerId = rs.getInt("id");
			
			//print line for test
			logger.info("print line for test: Uid: " + userId + ", " + "Pid: " + productId + ", " + "Cid: " + customerId + "Q= " + quantity + "ON= " + orderNumber);
			
			prs = con.prepareStatement(query.getString("db.insertToOrdersQuery"));
			prs.setInt(1, orderNumber);
			prs.setInt(2, productId);
			prs.setInt(3, userId);
			prs.setInt(4, customerId);
			prs.setInt(5, quantity);
			prs.executeUpdate();
			logger.info("Successful order");
			
			// Decrease the quantity in the inventory:
			updateQuantityOrder(quantity, productId);
			
		} catch (Exception e) {
			logger.error("Send data to DB error: " + e.getMessage());
		}
		
	}
	
	// Geri, 08.24 method for decrease the quantity in db when some one order a product		
	private void updateQuantityOrder(int quantity, int product_id){
		
		int quantityInDB;
		
		try {
			prs = con.prepareStatement(query.getString("db.getProductQuantityQuery"));
			prs.setInt(1, product_id);
			rs = prs.executeQuery();
			rs.next();
			quantityInDB = rs.getInt("quantity");
			prs = con.prepareStatement(query.getString("db.updateQuantityQuery"));
			prs.setInt(1, quantityInDB);
			prs.setInt(2, quantity);
			prs.setInt(3, product_id);
			prs.executeUpdate();
			logger.info("Successful update");
		} catch (Exception e) {
			logger.error("Update data DB error: " + e.getMessage());
		}
	}

	// Geri, 08.23 : Delete a Order from DB
	public void orderDelete(int orderNumber) {
		//updateQuantityDeleteOrder(orderNumber);
		try {
			prs = con.prepareStatement(query.getString("db.deleteOrderQuery"));
			prs.setInt(1, orderNumber);
			int test = prs.executeUpdate();
			if (test == 0) {
				logger.warn("No order in the DB with this number.");
				throw new RuntimeException("Not in DB.");
			}
			else{
				prs.executeUpdate();
				logger.info("Successfully deleted Order on this order number: " + orderNumber);
			}

		} catch (Exception e) {
			logger.error("Delete data from DB error: " + e.getMessage());
		}
	}
	
	// Geri, 08.24 method for increase the quantity in db when some one delete an order. Need to be re make.	
	/*private void updateQuantityDeleteOrder(int order_number){
		
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
				logger.error("Delete data DB error: " + e.getMessage());
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
			logger.info("print line for test: Uid: " + userId + ", " + "Pid: " + productId + ", " + "Cid: " + customerId");
			
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
			logger.info("Successful order update");
			
	}catch (Exception e) {
		logger.error("Send data to DB error: " + e.getMessage());
	}
		};
		
*/
		

	// main for test
	public static void main(String[] args) {
		ManageOrders orderManager = new ManageOrders();
		orderManager.order(6, "tom", "testP1", "Gizi",200);
		//orderManager.createOrderList();
		//orderManager.orderDelete(6);
		//orderManager.orderUpdate(5, "'tom'", "'testP1'", "'Gizi'", 5, 1000);
		//orderManager.updateQuantityOrder(100, 4);

	}

}
