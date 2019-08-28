package assignment1;
import assignment1.TCPServer;

public class PASS {
	public static String PASSCommand(String password) throws Exception {
		int status;
		
		Login loginFileHandler = new Login();
		
		// If already logged in, no need for password
		if (TCPServer.loggedIn == 1) { 
			return "!Logged in";
		}
		
		// If not logged in, and no user id has been specified
		if (TCPServer.loggedIn == 0) {
			return "-Wrong password, try again";
		}
		
		// Check login data for that account
		status = loginFileHandler.checkPassword(TCPServer.loggedInUserID, TCPServer.loggedInAccount, password);
		
		// Output result of command
		if (status == 0) {
			return "-Wrong password, try again";
		} else if (status == 1) {
			TCPServer.loggedIn = 4;
			return "+Send account";
		} else {
			TCPServer.loggedIn = 1;
			if (TCPServer.requestedDir != null) {
				String output = CDIR.CDIRCommand(TCPServer.requestedDir);
				TCPServer.requestedDir = null;
				return output;
			}
			return "!Logged in";
		}
	}
}
