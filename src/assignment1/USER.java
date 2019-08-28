package assignment1;
import assignment1.TCPServer;
//import assignment1.Login;

public class USER {
	
	public static String USERCommand(String userID_string) throws Exception {
		int userID; 
		int status;
		
		Login loginFileHandler = new Login();
		
		try {
			userID = Integer.parseInt(userID_string);
		} catch (Exception e) {
			return "-Invalid user-id, try again";
		}
	
		// Check login data for that user id
		status = loginFileHandler.checkUserID(userID);
		
		// Output result of command
		if (status == 0) {
			return "-Invalid user-id, try again";
		} else if (status == 1) {
			TCPServer.loggedIn = 2;
			TCPServer.loggedInUserID = userID;
			TCPServer.loggedInAccount = null;
			return "+User-id valid, send account and password";
		} else {
			TCPServer.loggedIn = 1;
			TCPServer.loggedInUserID = userID;
			TCPServer.loggedInAccount = null;
			return "!" + userID + " logged in";
		}
	}
}
