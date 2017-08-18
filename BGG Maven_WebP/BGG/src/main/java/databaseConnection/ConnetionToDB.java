package databaseConnection;

import java.sql.*;

public class ConnetionToDB {

	private Connection con;
	private Statement st;
	private ResultSet rs;
	
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
	
	public void getDataFromProduts(){
		try{
			String query = "SELECT * FROM Products";
			rs = st.executeQuery(query);
			while(rs.next()){
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");
				int productId = rs.getInt("id");
				
				System.out.println("Product name: " + name );
				
			}
			
			
		}catch(Exception e){
			System.out.println("Get data from DB error: " + e.getMessage() );
		}
	
	}
	public static void main(String[] args) {
		ConnetionToDB connect = new ConnetionToDB();
		connect.getDataFromProduts();
	}
}