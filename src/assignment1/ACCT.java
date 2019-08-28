package assignment1;

import assignment1.TCPServer;

public class ACCT {

	public static String ACCTCommand(String account) throws Exception {
		int status;

		Login loginFileHandler = new Login();

		// Check if already logged in
		if (TCPServer.loggedIn == 1) {
			return "!Account valid, logged-in";
		} else if (TCPServer.loggedIn == 0) {
			return "-Invalid account, try again";
		}

		// Check login status
		status = loginFileHandler.checkAccount(TCPServer.loggedInUserID, account);

		// Output result of command
		if (status == 0) {
			TCPServer.loggedIn = 2;
			TCPServer.loggedInAccount = null;
			return "-Invalid account, try again";
		} else if (status == 1) {
			TCPServer.loggedInAccount = account;
			if (TCPServer.loggedIn == 4) {
				TCPServer.loggedIn = 1;
				return "!Account valid, logged-in";
			} else {
				TCPServer.loggedIn = 3;
				return "+Account valid, send password";
			}
		} else {
			TCPServer.loggedIn = 1;
			TCPServer.loggedInAccount = account;
			if (TCPServer.requestedDir != null) {
				String output = CDIR.CDIRCommand(TCPServer.requestedDir);
				TCPServer.requestedDir = null;
				return output;
			}
			return "!Account valid, logged-in";
		}
	}
}
