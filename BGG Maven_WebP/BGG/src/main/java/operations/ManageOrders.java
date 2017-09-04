package operations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import databaseConnection.ConnetionToDB;

public class ManageOrders {

	private ConnetionToDB connection = new ConnetionToDB();
	private ResourceBundle query = ResourceBundle.getBundle("querys",new Locale("hu","HU"));
	private static final Logger logger = LogManager.getLogger(ManageOrders.class);
	
	public List<beans.Order> createOrderList() {
		List<beans.Order> Orders = new ArrayList<beans.Order>();

		try(Connection con = connection.openConnection();
			PreparedStatement prs = con.prepareStatement(query.getString("db.getAllOrderData"));
			ResultSet rs = prs.executeQuery();	
				) {
			
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
				System.out.println("ordN: " +orderNumber);
				logger.info("print line for test: Order number: " + orderNumber + " Orderd product: " + productName);
			}
		} catch (Exception e) {
			logger.error("Get data from DB error: " + e.getMessage());
		}

		return Orders;
	}

	public void order(int orderNumber, String userName, String productName,
			String customerName, int quantity) {
		
		ResultSet rs = null;
		
		try(Connection con = connection.openConnection();
			PreparedStatement prs = con.prepareStatement(query.getString("db.selectUserIdQuery"));
			PreparedStatement prs2 = con.prepareStatement(query.getString("db.selectProductIdQuery"));
			PreparedStatement prs3 = con.prepareStatement(query.getString("db.selectCostumerIdQuery"));
			PreparedStatement prs4 = con.prepareStatement(query.getString("db.insertToOrdersQuery"));
				) {		
			prs.setString(1, userName);
			rs = prs.executeQuery();
			int userId = 0;
			rs.next();
			if (rs.getRow() == 0) {
				throw new RuntimeException("User not found.");
			}
			userId = rs.getInt("id");

			
			prs2.setString(1, productName);
			rs = prs2.executeQuery();
			int productId = 0;
			rs.next();
			if (rs.getRow() == 0) {
				throw new RuntimeException("Product not found.");
			}
			productId = rs.getInt("id");

			
			prs3.setString(1, customerName);
			rs = prs3.executeQuery();
			int customerId = 0;
			rs.next();
			if (rs.getRow() == 0) {
				throw new RuntimeException("Customer not found.");
			}
			customerId = rs.getInt("id");
			
			//print line for test
			logger.info("print line for test: Uid: " + userId + ", " + "Pid: " + productId + ", " + "Cid: " + customerId + "Q= " + quantity + "ON= " + orderNumber);
			
			prs4.setInt(1, orderNumber);
			prs4.setInt(2, productId);
			prs4.setInt(3, userId);
			prs4.setInt(4, customerId);
			prs4.setInt(5, quantity);
			prs4.executeUpdate();
			logger.info("Successful order");
			
			// Decrease the quantity in the inventory:
			updateQuantityOrder(quantity, productId);
			
		} catch (Exception e) {
			logger.error("Send data to DB error: " + e.getMessage());
		}
		finally{
			try { rs.close(); } 
			catch (Exception e) { System.out.println("ResultSet can not be closed. Error: " + e.getMessage()); }
		}
		
	}

	
	private void updateQuantityOrder(int quantity, int product_id){
		
		int quantityInDB;
		
		try(Connection con = connection.openConnection();
			PreparedStatement prs = con.prepareStatement(query.getString("db.getProductQuantityQuery"));
			PreparedStatement prs2 = con.prepareStatement(query.getString("db.updateQuantityQuery"));
			) {
			
			prs.setInt(1, product_id);
			ResultSet rs = prs.executeQuery();
			rs.next();
			quantityInDB = rs.getInt("quantity");
			
			prs2.setInt(1, quantityInDB);
			prs2.setInt(2, quantity);
			prs2.setInt(3, product_id);
			prs2.executeUpdate();
			logger.info("Successful update");
			
		} catch (Exception e) {
			logger.error("Update data DB error: " + e.getMessage());
		}
	}


	public void orderDelete(int orderNumber) {
		//updateQuantityDeleteOrder(orderNumber);
		try(Connection con = connection.openConnection();
			PreparedStatement prs = con.prepareStatement(query.getString("db.deleteOrderQuery"));	
			) {
			
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
	
	//method for increase the quantity in db when some one delete an order. Need to be re make.	
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
		orderManager.order(7, "tom", "testP1", "Gergo Matrai",300);
		//orderManager.createOrderList();
		//orderManager.orderDelete(6);
		//orderManager.orderUpdate(5, "'tom'", "'testP1'", "'Gizi'", 5, 1000);
		//orderManager.updateQuantityOrder(100, 4);

	}

}
