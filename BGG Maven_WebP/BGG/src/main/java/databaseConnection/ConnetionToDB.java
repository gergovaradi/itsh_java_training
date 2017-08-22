package databaseConnection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import beans.Order;

public class ConnetionToDB {

	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	/* Geri, 08.21 :
	set the connection with the DB, all have to use her/ his own URL at "Connection URL" 
	and acc name / pass at "con". */
	public ConnetionToDB(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String connectionURL = "jdbc:mysql://localhost:3306/testdb?autoReconnect=true&useSSL=false";
			con = DriverManager.getConnection(connectionURL,"root","1234");
			st = con.createStatement();
		}catch(Exception e){
			System.out.println("DB connection error: " + e.getMessage());
		}
		
	}	
	// Geri, 08.21 :  Create a list of products from DB data.
	public List<beans.Product> createProductList(){
		List<beans.Product> productList = new ArrayList<beans.Product>();
		try{
			String query = "SELECT * FROM Products";
			rs = st.executeQuery(query);
			while(rs.next()){
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");
				// int productId = rs.getInt("id");
				beans.Product product = new beans.Product(name, price, quantity);
				productList.add(product);
				//print line for test
				System.out.println("Product name: " + name );
				
			}
			
		}catch(Exception e){
			System.out.println("Get data from DB error: " + e.getMessage() );
		}

		return productList;
	}
	// Geri, 08.21 : Create a list of user accounts from DB data.
	public List<beans.UserAccount> createUserAccountList(){
		List<beans.UserAccount> UserAccountList = new ArrayList<beans.UserAccount>();
		try{
			String query = "SELECT * FROM User_account";
			rs = st.executeQuery(query);
			while(rs.next()){
				String name = rs.getString("name");
				String password = rs.getString("password");
				String salt = rs.getString("salt");
				beans.UserAccount userAcount = new beans.UserAccount(name, password, salt);
				UserAccountList.add(userAcount);
				//print line for test
				System.out.println("User pass: " + password );
				
			}
			
		}catch(Exception e){
			System.out.println("Get data from DB error: " + e.getMessage() );
		}

		return UserAccountList;
	}
	
	// Geri, 08.21 : Create a list of user informations from DB data.
	public List<beans.UserInformation> createUserInformationList(){
		List<beans.UserInformation> UserInformationList = new ArrayList<beans.UserInformation>();
		try{
			String query = "SELECT * FROM User_information";
			rs = st.executeQuery(query);
			while(rs.next()){
				String address = rs.getString("address");
				String email = rs.getString("email");
				String fullName = rs.getString("full_name");
				String phone = rs.getString("phone");
				beans.UserInformation userInformation = new beans.UserInformation(address, email, fullName, phone);
				UserInformationList.add(userInformation);
				//print line for test
				System.out.println("User phone-number: " + phone + " " );
				
			}
			
		}catch(Exception e){
			System.out.println("Get data from DB error: " + e.getMessage() );
		}

		return UserInformationList;
	}
	// Geri, 08.21 : Create a list of orders from DB data.
	public List<beans.Order> createOrderList(){
		List<beans.Order> Orders = new ArrayList<beans.Order>();
		try{
			String query = "SELECT orders.order_number,orders.date,orders.quantity,products.product_name,user_account.name FROM orders JOIN products ON orders.product_id = products.id JOIN user_account ON orders.user_id = user_account.id;";
			rs = st.executeQuery(query);
			
			while(rs.next()){
				int orderNumber = rs.getInt("order_number");
				LocalDate orderDate = rs.getDate("date").toLocalDate();
				String userName = rs.getString("name");
				String productName = rs.getString("product_name");
				int quantity = rs.getInt("quantity");
				beans.Order order = new beans.Order(orderNumber, orderDate, userName, productName, quantity);
				Orders.add(order);
				//print line for test
				System.out.println("Order number: " + orderNumber + " Orderd product: " + productName);
				
			}
			
		}catch(Exception e){
			System.out.println("Get data from DB error: " + e.getMessage() );
		}

		return Orders;
	}
	
	//Geri, 08.21 : Execute inserts to table "user_information" and "user_account" when someone make a registration.
	// IMPORTANT : When we get a String from front-end we should concat between '+..+' like this: 'example'
	public void registration( String name, String password, String salt, String fullName,
				String email, String phone, String address ){
		try{
			String AccQuerry = "INSERT INTO user_account (name, password, salt) VALUES(" + name + ", " 
			+ password + "," + salt +"); "; 
			String InfQuerry ="INSERT INTO user_information (address, email, full_Name, phone) VALUES(" + address + ","
			+ email + "," + fullName + "," + phone +");";
				
			st.executeUpdate(AccQuerry);
			st.executeUpdate(InfQuerry);
			System.out.println("Succsesfull registration.");
			}
		catch(Exception e){
			System.out.println("Send data to DB error: " + e.getMessage());
			}							
		}
	
	//Geri, 08.22 : save an order in DB
	public void order( int orderNumber, String userName, String productName,
			int quantity){
		
	try{
		String selectUserIdQuerry = "SELECT id FROM user_account WHERE name =" + userName;
		String selectProductIdQerry = "SELECT id FROM products WHERE product_name = " + productName;
		
		
		rs = st.executeQuery(selectUserIdQuerry);
		// set the cursor to the correct place (it is default before the first row)
		rs.next();
		// getRow gives back the number of rows of resultSet, if 0 then the user is not in the DB 
		if(rs.getRow() == 0){
			//System.out.println(rs.getRow()); ---> give 0 if not in DB 
			throw new RuntimeException("User not found.");
		}
		int userId = rs.getInt("id");
		rs = st.executeQuery(selectProductIdQerry);
		rs.next();
		if(rs.getRow() == 1){
			throw new RuntimeException("Product not found.");
		}
		int productId = rs.getInt("id");
		// print line for test
		System.out.println("Uid: " + userId + ", " + "Pid: " + productId);
		
		String insertToOrdersQuerry = "INSERT INTO Orders (order_number, product_id, user_id, quantity) VALUES("+orderNumber + "," + productId +"," + userId +"," + quantity + ")";
		st.executeUpdate(insertToOrdersQuerry);
		System.out.println("Succsesfull order");
		}
	catch(Exception e){
		System.out.println("Send data to DB error: " + e.getMessage());
		}							
	}
	
	
	// Geri, 08.21 :  main method for testing
	public static void main(String[] args) {
		ConnetionToDB connect = new ConnetionToDB();
		/*connect.registration("'kbela'", "'k123'", "'salt1'", "'Kovács Béla'",
				"'kovacs.bela@gmail.com'", "'06305672966'", "'1191 Bp Fõ u.35.'");*/
		//connect.createProductList();
		//connect.createUserAccountList();
		//connect.createUserInformationList();
		//connect.createOrderList();
		connect.order(2, "'jerry'", "'TEST_Product1'", 5);
		
	}
}