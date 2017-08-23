package operations;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import databaseConnection.ConnetionToDB;

public class ManageUsers {

	private ConnetionToDB connection = new ConnetionToDB();
	private ResultSet rs = connection.getRs();
	private Statement st = connection.getSt();
	
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
	
}
