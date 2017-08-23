package databaseConnection;

import java.time.LocalDate;
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
	//Getters and setters
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
	public Statement getSt() {
		return st;
	}
	public void setSt(Statement st) {
		this.st = st;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	
}