package databaseConnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;


import com.mysql.jdbc.PreparedStatement;


public class ConnetionToDB {

	
	private static final Logger logger = LogManager.getLogger(ConnetionToDB.class);
	
	/** set the connection with the DB*/
	public Connection openConnection(){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String connectionURL = "jdbc:mysql://localhost:3306/testdb?autoReconnect=true&useSSL=false";
			con = DriverManager.getConnection(connectionURL,"root","1234");
			logger.info("DB connection created");
		}catch(Exception e){
			logger.error("DB connection error: " + e.getMessage());
		}
		return con;
	}
	

	
}