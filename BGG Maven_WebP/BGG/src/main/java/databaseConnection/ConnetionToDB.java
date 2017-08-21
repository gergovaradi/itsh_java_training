package databaseConnection;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

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
	public List<beans.Product> getDataFromProduts(){
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
				System.out.println("Product name: " + name );
				
			}
			
		}catch(Exception e){
			System.out.println("Get data from DB error: " + e.getMessage() );
		}

		return productList;
	}
	// Geri, 08.21 : Create a list of user accounts from DB data.
	public List<beans.UserAccount> getDataFromUserAccount(){
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
				System.out.println("User pass: " + password );
				
			}
			
		}catch(Exception e){
			System.out.println("Get data from DB error: " + e.getMessage() );
		}

		return UserAccountList;
	}
	
	// Geri, 08.21 : Create a list of user informations from DB data.
	public List<beans.UserInformation> getDataFromUserInformation(){
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
				System.out.println("User phone-number: " + phone );
				
			}
			
		}catch(Exception e){
			System.out.println("Get data from DB error: " + e.getMessage() );
		}

		return UserInformationList;
	}
	
	
	// Geri, 08.21 :  main method for testing
	public static void main(String[] args) {
		ConnetionToDB connect = new ConnetionToDB();
		connect.getDataFromProduts();
		connect.getDataFromUserAccount();
		connect.getDataFromUserInformation();
	}
}