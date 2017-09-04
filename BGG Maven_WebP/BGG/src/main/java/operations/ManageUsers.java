package operations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import databaseConnection.ConnetionToDB;

public class ManageUsers {

	private ConnetionToDB connection = new ConnetionToDB();
	private ResourceBundle query = ResourceBundle.getBundle("querys",new Locale("hu","HU"));
	private static final Logger logger = LogManager.getLogger(ManageUsers.class);
	
	
		public List<beans.UserAccount> createUserAccountList(){
			List<beans.UserAccount> UserAccountList = new ArrayList<beans.UserAccount>();
			try(Connection con = connection.openConnection();
				PreparedStatement prs = con.prepareStatement(query.getString("db.getAllUserdata"));
				ResultSet rs = prs.executeQuery();	
				) {
				
				while(rs.next()){
					String name = rs.getString("name");
					String password = rs.getString("password");
					String salt = rs.getString("salt");
					beans.UserAccount userAcount = new beans.UserAccount(name, password, salt);
					UserAccountList.add(userAcount);
					//print line for test
					logger.info("print line for test: User pass: " + password);				
				}
				
			}catch(Exception e){
				logger.error("Get data from DB error: " + e.getMessage());
			}

			return UserAccountList;
		}
	
}
