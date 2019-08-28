package assignment1;
import assignment1.TCPServer;

public class CDIR {
	public static String CDIRCommand(String newDir) {
		if (TCPServer.loggedIn == 0) { // Check if a userID is specified
			return "-Can't connect to directory because: No UserID";
		} else { 
			String result = LIST.listDir(newDir, false);
			if (result.equals("-Directory path doesn't exist")) { // Check if the directory exists
				return "-Can't connect to directory because: Directory doesn't exist";
			}

			if (TCPServer.loggedIn == 1) { // User is logged in		
				TCPServer.currentDir = newDir;
				return "+!Changed working dir to " + newDir;
			} else { // User is not logged in
				TCPServer.requestedDir = newDir;
				return "+directory ok, send account/password";
			}
		}
	}
}
