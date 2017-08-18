package databaseConnection;

import java.sql.*;

public class ConnetionToDB {

	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public DBConnect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String connectionURL = "jdbc:mysql://localhost:3306/testdb?autoReconnect=true&useSSL=false";
			con = DriverManager.getConnection(connectionURL,"root","1234");
			st = con.createStatement();
		}catch(Exception e){
			System.out.println("DB connection error: " + e.getMessage());
		}
		
	}	
	
	
}
