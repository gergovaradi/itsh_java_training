package databaseConnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;


import com.mysql.jdbc.PreparedStatement;


public class ConnetionToDB {

	private Connection con;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement prs;
	private static final Logger logger = LogManager.getLogger(ConnetionToDB.class);
	
	/** Geri, 08.21 :
	set the connection with the DB, all have to use her/ his own URL at "Connection URL" 
	and acc name / pass at "con". */
	public ConnetionToDB(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String connectionURL = "jdbc:mysql://localhost:3306/testdb?autoReconnect=true&useSSL=false";
			con = DriverManager.getConnection(connectionURL,"root","1234");
			st = con.createStatement();
			logger.info("DB connection created");
		}catch(Exception e){
			logger.error("DB connection error: " + e.getMessage());
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
	public PreparedStatement getPrs() {
		return prs;
	}
	public void setPrs(PreparedStatement prs) {
		this.prs = prs;
	}
	
}